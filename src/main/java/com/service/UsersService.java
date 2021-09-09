package com.service;

import com.DTO.UserDTO;
import com.DTO.RegisterUser;
import com.entity.CustomUserDetails;
import com.entity.Users;

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
        return repository.findByEmail(email).orElse(null);
    }

    public Users getUserLogin() {
        CustomUserDetails login = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return login.getUser();
    }
////////// Register
    public Users register(RegisterUser registerUser) {
        Users user = new Users();
        user.setUsername(registerUser.getUsername());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setEmail(registerUser.getEmail());
        user.setPhone(registerUser.getPhone());
//        user.setDivisionUser(user.getDivisionUser());

        return repository.save(user);
    }



////////Put Profile
    public Users update(UserDTO dto) {
        Users users =getUserLogin();
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        dto.loadFromEntity(users);
        return repository.save(users);
    }





}
