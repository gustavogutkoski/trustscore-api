package com.gutkoski.trustscore.controller;

import com.gutkoski.trustscore.dto.UserRequestDTO;
import com.gutkoski.trustscore.dto.UserResponseDTO;
import com.gutkoski.trustscore.service.interfaces.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
    UserResponseDTO createdUser = userService.createUser(userRequestDTO);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    UserResponseDTO userResponseDTO = userService.getUserById(id);
    return ResponseEntity.ok(userResponseDTO);
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> updateUser(
      @PathVariable Long id, @RequestBody UserRequestDTO updatedUser) {
    UserResponseDTO userResponseDTO = userService.updateUser(id, updatedUser);
    return ResponseEntity.ok(userResponseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
