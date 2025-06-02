package com.gutkoski.trustscore.service;

import com.gutkoski.trustscore.dto.ProductRequestDTO;
import com.gutkoski.trustscore.dto.ProductResponseDTO;
import com.gutkoski.trustscore.entity.Product;
import com.gutkoski.trustscore.mapper.ProductMapper;
import com.gutkoski.trustscore.repository.ProductRepository;
import com.gutkoski.trustscore.service.interfaces.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
    Product savedProduct = productRepository.save(productMapper.toEntity(productRequestDTO));
    return productMapper.toDTO(savedProduct);
  }

  @Override
  public ProductResponseDTO getProductById(Long id) {
    Product existingProduct =
        productRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    return productMapper.toDTO(existingProduct);
  }

  @Override
  public List<ProductResponseDTO> getAllProducts() {
    return productRepository.findAll().stream()
        .map(productMapper::toDTO)
        .collect(Collectors.toList());
  }

  @Override
  public ProductResponseDTO updateProduct(Long id, ProductRequestDTO updatedProduct) {
    Product existingProduct =
        productRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    existingProduct.setName(updatedProduct.name());
    existingProduct.setDescription(updatedProduct.description());
    return productMapper.toDTO(productRepository.save(existingProduct));
  }

  @Override
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
