package com.gutkoski.trustscore.service.interfaces;

import com.gutkoski.trustscore.dto.UserRequestDTO;
import com.gutkoski.trustscore.dto.UserResponseDTO;
import java.util.List;

public interface UserService {
  UserResponseDTO createUser(UserRequestDTO userRequestDTO);

  UserResponseDTO getUserById(Long id);

  List<UserResponseDTO> getAllUsers();

  UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

  void deleteUser(Long id);
}
