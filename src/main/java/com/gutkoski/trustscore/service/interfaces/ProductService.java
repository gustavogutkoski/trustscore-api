package com.gutkoski.trustscore.service.interfaces;

import com.gutkoski.trustscore.dto.ProductRequestDTO;
import com.gutkoski.trustscore.dto.ProductResponseDTO;
import java.util.List;

public interface ProductService {
  ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

  ProductResponseDTO getProductById(Long id);

  List<ProductResponseDTO> getAllProducts();

  ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);

  void deleteProduct(Long id);
}
