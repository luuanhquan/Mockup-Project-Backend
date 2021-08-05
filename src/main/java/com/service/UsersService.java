package com.service;

import com.entity.CustomUserDetails;
import com.entity.Users;
import com.enums.STATUS_REGISTER;
import com.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Users save(Users s) {
        return repository.save(s);
    }

    public List<Users> findAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Users> findById(Integer id) {
        return repository.findById(id);
    }

    public Boolean checkDuplicate(Users users) {

            // check existed user-----------------------------------------
            if (findByUsername(users.getUsername()) != null) {
                return true;
            }
            // check existed mail-----------------------------------------
            if (findUserByEmail(users.getEmail()) != null) {
                return true;
            }


            return false;

    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        Users user = repository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(user);
    }

    public Users findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }



    public Users findUserByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public Users getUserLogin(){
        CustomUserDetails login= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return login.getUser();
    }
}
