package com.gutkoski.trustscore.mapper;

import com.gutkoski.trustscore.dto.ProductRequestDTO;
import com.gutkoski.trustscore.dto.ProductResponseDTO;
import com.gutkoski.trustscore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  ProductResponseDTO toDTO(Product product);

  Product toEntity(ProductRequestDTO productRequestDTO);
}
