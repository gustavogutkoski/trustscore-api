package com.gutkoski.trustscore.entities.user.mapper;

import com.gutkoski.trustscore.entities.user.User;
import com.gutkoski.trustscore.entities.user.dto.UserRequestDTO;
import com.gutkoski.trustscore.entities.user.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toDTO(User user);

    User toEntity(UserRequestDTO userRequestDTO);
}
