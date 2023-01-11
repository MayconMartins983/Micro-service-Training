package com.example.controleservice.service;

import com.example.controleservice.dto.IndexesCSV;
import com.example.controleservice.dto.ProcessNoteResponse;
import com.example.controleservice.enums.ESemestre;
import com.example.controleservice.enums.EStatusStudent;
import com.example.controleservice.models.ProcessamentoNota;
import com.example.controleservice.repository.ProcessamentoNotaRepository;
import com.example.controleservice.utils.CvsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.controleservice.utils.CvsUtils.validatedExtensionFile;
import static java.lang.Boolean.*;

@Service
public class ProcessNoteService {

    public static final double NOTE_FOR_ROUDING = 6.8;
    public static final double NOTE_FOR_APROVED = 7.0;

    @Autowired
    private ProcessamentoNotaRepository repository;
    @Autowired
    private StudentService studentService;

    @Transactional
    public List<ProcessNoteResponse> processNotesFromCsv(MultipartFile file, String delimiter, UUID idStudent) {
        validatedExtensionFile(file);
        var linesCsv = CvsUtils.readCsv(file);
        var indexesFieldsHeader = searchIndexByHeader(linesCsv.get(0), delimiter);
        var listOfArray = linesCsv
                .stream()
                .skip(1)
                .map(e -> e.split(delimiter))
                .collect(Collectors.toList());

        return processNotes(listOfArray, indexesFieldsHeader, idStudent);
    }

    private List<ProcessNoteResponse> processNotes(List<String[]> listDataFromCsv, IndexesCSV indexesHeader, UUID idStudent) {
        var studentById = studentService.findStudentById(idStudent);

        return listDataFromCsv
                .stream()
                .map(student -> {
                    var processNoteModel = ProcessamentoNota.builder()
                            .nameCourse(student[indexesHeader.getNameCourseIndex()])
                            .semester(ESemestre.valueOf(student[indexesHeader.getSemesterIndex()].toUpperCase()))
                            .noteAct1(Double.valueOf(student[indexesHeader.getNoteAct1Index()]))
                            .noteAct2(Double.valueOf(student[indexesHeader.getNoteAct2Index()]))
                            .noteAct3(Double.valueOf(student[indexesHeader.getNoteAct3Index()]))
                            .noteAct4(Double.valueOf(student[indexesHeader.getNoteAct4Index()]))
                            .noteAct5(Double.valueOf(student[indexesHeader.getNoteAct5Index()]))
                            .noteProof1(Double.valueOf(student[indexesHeader.getNoteProof1Index()]))
                            .noteProof2(Double.valueOf(student[indexesHeader.getNoteProof2Index()]))
                            .noteProof3(Double.valueOf(student[indexesHeader.getNoteProof3Index()]))
                            .noteExam(Double.valueOf(student[indexesHeader.getNoteExamIndex()]))
                            .teacherConsiderations(student[indexesHeader.getTeacherConsiderationsIndex()])
                            .student(studentById)
                            .build();

                    var averageNotes = processNoteModel.getSumNotes() / 8;

                    if (processNoteModel.getNoteExam() == null || processNoteModel.getNoteExam() == 0) {
                        verifyNotesByStudentNotExam(processNoteModel, averageNotes);
                    } else {
                        verifyNotesByStudentExam(processNoteModel);
                    }

                    var processSaved = repository.save(processNoteModel);

                    return ProcessNoteResponse
                            .builder()
                            .id(processSaved.getStudent().getId())
                            .nameStudent(processSaved.getStudent().getFullName())
                            .statusStudent(processSaved.getStatusAluno())
                            .noteFinal(processSaved.getNoteExam() == null || processSaved.getNoteExam() == 0
                                    ? averageNotes
                                    : processSaved.getNoteExam())
                            .build();

                }).collect(Collectors.toList());
    }

    private void verifyNotesByStudentNotExam(ProcessamentoNota processamentoNota, Double noteFinal) {
        if (noteFinal < NOTE_FOR_ROUDING) {
            processamentoNota.setStatusAluno(EStatusStudent.IN_EXAM);
            processamentoNota.setExam(TRUE);
        } else if (noteFinal >= NOTE_FOR_ROUDING && noteFinal < NOTE_FOR_APROVED) {
            processamentoNota.setStatusAluno(EStatusStudent.APROVADO);
            processamentoNota.setRounding(TRUE);
        } else {
            processamentoNota.setStatusAluno(EStatusStudent.APROVADO);
        }
    }

    private void verifyNotesByStudentExam(ProcessamentoNota processamentoNota) {
        var note = processamentoNota.getNoteExam();

        if (note < NOTE_FOR_ROUDING) {
            processamentoNota.setStatusAluno(EStatusStudent.REPROVADO);
        } else if (note >= NOTE_FOR_ROUDING && note < NOTE_FOR_APROVED) {
            processamentoNota.setStatusAluno(EStatusStudent.APROVADO);
            processamentoNota.setRounding(TRUE);
        } else {
            processamentoNota.setStatusAluno(EStatusStudent.APROVADO);
        }
    }

    private IndexesCSV searchIndexByHeader(String header, String delimiter) {
        var fieldsHeader = header.split(delimiter);

        return IndexesCSV.builder()
                .nameCourseIndex(Arrays.asList(fieldsHeader).indexOf("NameCourse"))
                .semesterIndex(Arrays.asList(fieldsHeader).indexOf("Semester"))
                .noteAct1Index(Arrays.asList(fieldsHeader).indexOf("NoteAct1"))
                .noteAct2Index(Arrays.asList(fieldsHeader).indexOf("NoteAct2"))
                .noteAct3Index(Arrays.asList(fieldsHeader).indexOf("NoteAct3"))
                .noteAct4Index(Arrays.asList(fieldsHeader).indexOf("NoteAct4"))
                .noteAct5Index(Arrays.asList(fieldsHeader).indexOf("NoteAct5"))
                .noteProof1Index(Arrays.asList(fieldsHeader).indexOf("NoteProof1"))
                .noteProof2Index(Arrays.asList(fieldsHeader).indexOf("NoteProof2"))
                .noteProof3Index(Arrays.asList(fieldsHeader).indexOf("NoteProof3"))
                .noteExamIndex(Arrays.asList(fieldsHeader).indexOf("NoteExam"))
                .teacherConsiderationsIndex(Arrays.asList(fieldsHeader).indexOf("TeacherConsiderations"))
                .build();
    }
}
