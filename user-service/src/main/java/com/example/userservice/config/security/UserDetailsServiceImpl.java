package com.example.userservice.config.security;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    //Aqui criamos o método pro security verificar no banco se existe tal pessoa pelo email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userModel = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        return UserDetailsImpl.buildUserDetails(userModel);
    }

    public UserDetails loadUserByUserId(UUID id) throws UsernameNotFoundException {
        User userModel = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
        return UserDetailsImpl.buildUserDetails(userModel);
    }
}
