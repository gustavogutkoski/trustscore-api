package com.gutkoski.trustscore.service;

import com.gutkoski.trustscore.dto.UserRequestDTO;
import com.gutkoski.trustscore.dto.UserResponseDTO;
import com.gutkoski.trustscore.entity.User;
import com.gutkoski.trustscore.mapper.UserMapper;
import com.gutkoski.trustscore.repository.UserRepository;
import com.gutkoski.trustscore.service.interfaces.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
    User user = userMapper.toEntity(userRequestDTO);
    User savedUser = userRepository.save(user);
    return userMapper.toDTO(savedUser);
  }

  @Override
  public UserResponseDTO getUserById(Long id) {
    User existingUser =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    return userMapper.toDTO(existingUser);
  }

  @Override
  public List<UserResponseDTO> getAllUsers() {
    return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
    User existingUser =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    existingUser.setName(userRequestDTO.name());
    existingUser.setEmail(userRequestDTO.email());
    User updatedUser = userRepository.save(existingUser);
    return userMapper.toDTO(updatedUser);
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
