package com.service;

import com.entity.CustomUserDetails;
import com.entity.Users;
import com.entity.enums.STATUS_REGISTER;
import com.repositories.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UsersService implements UserDetailsService {
    public static final Logger logger = LogManager.getLogger(UsersService.class);

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
        logger.info("Start registerNewUser");
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
            logger.info("Exception on registerNewUser: " + ex.getMessage());
            return STATUS_REGISTER.Error_OnSystem;
        }
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        Users user = repository.findByUsername(username);
        if (user == null) {
            System.out.println("failed");
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

}
