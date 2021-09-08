package com.service;

import com.DTO.RegisterUser;
import com.DTO.UserDTO;
import com.entity.CustomUserDetails;
import com.entity.Users;

import com.enums.RESULT;
import com.exception.MyException;
import com.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Users findById(Integer id) {
        Users users = repository.getById(id);
        if (users == null) {
            throw new MyException(RESULT.USERS_NOT_FOUND);
        }
        return users;
    }

    public Users findOne(String email) {
        return repository.findByEmail(email);
    }


    public Boolean checkDuplicate(Users users) {

            // check existed user-----------------------------------------
            if (findByUsername(users.getUsername()) != null) {
               return true;

            }
            // check existed mail-----------------------------------------
            if (findUserByEmail(users.getEmail()) != null) {
               return  true;
            }
            return false;
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

    public Users findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

////////// Register
    public Users register(RegisterUser registerUser) {
        Users newUser = new Users();
        newUser.setUsername(registerUser.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        newUser.setEmail(registerUser.getEmail());
        newUser.setPhone(registerUser.getPhone());
        return repository.save(newUser);
    }

////////Update Profile-


    public Users update(UserDTO dto) throws ParseException {
        Users updateUser =repository.getById(dto.getId());
        updateUser.loadFromDTO(dto);
        return repository.save(updateUser);
    }




    public Users getUserLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
           return  ((CustomUserDetails)principal).getUser();
        } else {
            return findByUsername(principal.toString());
        }

    }




}




