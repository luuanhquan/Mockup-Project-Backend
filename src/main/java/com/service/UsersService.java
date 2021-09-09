package com.service;

import com.DTO.ChangePassRequest;
import com.DTO.UserDTOE;
import com.DTO.UserDTO;
import com.DTO.RegisterUser;
import com.entity.CustomUserDetails;
import com.entity.Users;

import com.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;
import java.util.Optional;

@Service
@Transactional
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepository repository;




    public Users save(Users s) {
        return repository.save(s);
    }


    public List<Users> findAll() {
        return repository.findAll();
    }
    public List<UserDTOE> findAlls(){
        return repository.findalls();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Users findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public UserDTOE findUserById(Integer id){

        return repository.findUserById(id);
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


    public Users findUserByEmail(String email){
        return repository.findUserByEmail(email);
    }

    public Users getUserLogin() {
        CustomUserDetails login = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return login.getUser();
    }
    @Autowired
    AES aes;

    public void changePass(ChangePassRequest request) {
        String key= aes.decrypt(request.getKey());
        repository.changePass(Integer.valueOf(key.substring(12,key.length())),new BCryptPasswordEncoder().encode(request.getPassword()));
    }

    public Page<UserDTOE> findAllDTO(int pageSize, int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<UserDTOE> page= repository.findAllDTO(pageable);
        return page;

    }

    public Users addUser(Users users) {
        return repository.save(users);
    }

    public Users updateUser(Users users) {
        return repository.saveAndFlush(users);
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





    public List<UserDTOE> search(String seachByRole,int seachByName,long seachByStatus) {

        return repository.seach(seachByRole,seachByName,seachByStatus);
    }
}
