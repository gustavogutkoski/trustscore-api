package com.gutkoski.trustscore.mapper;

import com.gutkoski.trustscore.entity.User;
import com.gutkoski.trustscore.dto.UserRequestDTO;
import com.gutkoski.trustscore.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toDTO(User user);

    User toEntity(UserRequestDTO userRequestDTO);
}
