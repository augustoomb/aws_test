package br.com.microservices.orchestrated.orderservice.core.mock;

import br.com.microservices.orchestrated.orderservice.core.dto.ProductRequest;
import br.com.microservices.orchestrated.orderservice.core.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MockProduct {

    public Product mockEntity(Integer number) {
        Product product = new Product();
        product.setId(number);
        product.setName("Name mock " + number);
        product.setValue(number.doubleValue());

        return product;
    }

    public ProductRequest mockDTO(Integer number) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Name mock " + number);
        productRequest.setValue(number.doubleValue());

        return productRequest;
    }

    public List<Product> mockEntityList() {
        List<Product> products = new ArrayList<Product>();

        for (int i = 0; i < 5; i++) {
            products.add(mockEntity(i));
        }

        return products;
    }
}
