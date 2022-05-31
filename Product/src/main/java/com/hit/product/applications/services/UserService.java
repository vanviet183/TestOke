package com.hit.product.applications.services;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.domains.dtos.UserDto;
import com.hit.product.domains.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface UserService {

    List<User> getListUser();

    User getUserById(Long id);

    User registerUser(UserDto userDto, HttpServletRequest request);

    User updateUser(Long id, UserDto userDto);

    TrueFalseResponse deleteUserById(Long id);

    User findUserByUsername(String username);

    User setAvatar(Long id, MultipartFile multipartFile);
}
