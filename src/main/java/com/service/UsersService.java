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
        return (Users) repository.findAll();
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

    public STATUS_REGISTER registerNewUser(Users users) {
        try {
            // check existed user-----------------------------------------
            if (findByUsername(users.getUsername()) != null) {
                return STATUS_REGISTER.Existed_Username;
            }
            // check existed mail-----------------------------------------
            if (findUserByEmail(users.getEmail()) != null) {
                return STATUS_REGISTER.Existed_Email;
            }

            // save user--------------------------------------------
            repository.save(users);

            return STATUS_REGISTER.Success;
        } catch (Exception ex) {
            return STATUS_REGISTER.Error_OnSystem;
        }
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        Users user = repository.findByUsername(username);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(user);
    }

    public Users findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Users registerNewUserAccount(Users account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.save(account);
    }

    public Users findUserByEmail(String email) {
        return StreamSupport.stream(repository.findByEmail(email).spliterator(), false).findFirst().orElse(null);
    }

    public Users getUserLogin() {
        CustomUserDetails login = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return login.getUser();
    }

}
