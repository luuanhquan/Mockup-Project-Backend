package com.service;

import com.entity.CustomUserDetails;
import com.entity.Division;
import com.entity.DivisionUser;
import com.entity.Users;
import com.entity.enums.USERROLE;
import com.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsersRepository repository;

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


    public Users registerNewUserAccount(Users account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.save(account);
    }

    @Autowired
    DivisionUserService divisionUserService;

    public List<String> getEmail(Users user, Integer newDivisionId){
        Division oldDivisionId= divisionUserService.findByUserId(user.getId());
        Users oldBoss= divisionUserService.findBoss(oldDivisionId);
        Users newBoss= divisionUserService.findBoss(new DivisionService().findById(newDivisionId).orElse(null));

        List<String> listEmail= new ArrayList<>();
        listEmail.add(oldBoss.getEmail());
        listEmail.add(newBoss.getEmail());
        return listEmail;
    }
}
