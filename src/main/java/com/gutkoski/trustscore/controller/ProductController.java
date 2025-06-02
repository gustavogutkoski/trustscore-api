package com.gutkoski.trustscore.controller;

import com.gutkoski.trustscore.dto.ProductRequestDTO;
import com.gutkoski.trustscore.dto.ProductResponseDTO;
import com.gutkoski.trustscore.service.interfaces.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<ProductResponseDTO> createProduct(
      @RequestBody ProductRequestDTO productRequestDTO) {
    return new ResponseEntity<>(
        productService.createProduct(productRequestDTO), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> updateProduct(
      @PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
    return ResponseEntity.ok(productService.updateProduct(id, productRequestDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
