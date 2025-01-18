package com.gutkoski.trustscore.entities.user;

import com.gutkoski.trustscore.entities.user.dto.UserRequestDTO;
import com.gutkoski.trustscore.entities.user.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    void deleteUser(Long id);
}
