package com.service;

import com.entity.CustomUserDetails;
import com.entity.Division;
import com.entity.Users;
import com.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
        if (user == null) {
            System.out.println("failed");
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));

        return new CustomUserDetails(user);
    }
    public Users findByUsername(String username){
        return repository.findByUsername(username);
    }


    public Users registerNewUserAccount(Users account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.save(account);
    }
}

