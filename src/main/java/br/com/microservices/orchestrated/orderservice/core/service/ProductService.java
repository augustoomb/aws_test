package br.com.microservices.orchestrated.orderservice.core.service;

import br.com.microservices.orchestrated.orderservice.core.dto.ProductRequest;
import br.com.microservices.orchestrated.orderservice.core.model.Product;
import br.com.microservices.orchestrated.orderservice.core.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {

        var product = Product
                .builder()
                .name(productRequest.getName())
                .value(productRequest.getValue())
                .build();

        log.info("PRODUTO: {}", product);

        productRepository.save(product);
    }
}
