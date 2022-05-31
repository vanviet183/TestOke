package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.commons.ERole;
import com.hit.product.applications.exceptions.NotFoundException;
import com.hit.product.applications.repositories.RoleRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.applications.repositories.VerificationTokenRepository;
import com.hit.product.applications.services.UserService;
import com.hit.product.applications.utils.UploadFile;
import com.hit.product.domains.dtos.UserDto;
import com.hit.product.domains.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    UploadFile uploadFile;

    @Override
    public List<User> getListUser() {
        return userRepository.findByStatusIsTrue();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        checkUserException(user);
        return user.get();
    }

    @Override
    public User registerUser(UserDto userDto, final HttpServletRequest request) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(roleRepository.findByName(ERole.ROLE_USER).get()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        checkUserException(user);
        modelMapper.map(userDto, user);
        user.get().setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user.get());
    }

    @Override
    public TrueFalseResponse deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        checkUserException(user);
        userRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

//    @Override
//    public User findUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsernameAndStatus(username, true);
    }

    @Override
    public User setAvatar(Long id, MultipartFile multipartFile) {
        Optional<User> user = userRepository.findById(id);
        checkUserException(user);

        if(user.get().getAvatar() != null) {
            uploadFile.removeFileFromUrl(user.get().getAvatar());
        }
        user.get().setAvatar(uploadFile.getUrlFromFile(multipartFile));
        userRepository.save(user.get());
        return user.get();
    }

    private void checkUserException(Optional<User> user) {
        if(user.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }




}
