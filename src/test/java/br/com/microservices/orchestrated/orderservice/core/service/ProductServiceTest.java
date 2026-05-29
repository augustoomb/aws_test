package br.com.microservices.orchestrated.orderservice.core.service;

import br.com.microservices.orchestrated.orderservice.core.dto.ProductRequest;
import br.com.microservices.orchestrated.orderservice.core.mock.MockProduct;
import br.com.microservices.orchestrated.orderservice.core.model.Product;
import br.com.microservices.orchestrated.orderservice.core.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    MockProduct input;

    @Mock
    ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setUp() {
        input = new MockProduct();
//        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct() {
        // Arrange (Preparação)
        ProductRequest request = input.mockDTO(1);
        Product product = input.mockEntity(1);

        // any() porque o Product é instanciado DENTRO do metodo do service
        when(repository.save(any(Product.class))).thenReturn(product);

        // Act (Ação) - Como é void, não atribuímos a nenhuma variável
        service.createProduct(request);

        // Verifica se o metodo save do repositório foi chamado exatamente 1 vez
        verify(repository, times(1)).save(any(Product.class));



        // Assert -------------------------------------------------------
        // 1. Criamos o captor para a classe Product
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);

        // 2. Verificamos se o save foi chamado e capturamos o argumento enviado a ele
        verify(repository, times(1)).save(productCaptor.capture());

        // 3. Pegamos o objeto real que o seu Service criou
        Product productCriadoInternamente = productCaptor.getValue();

        // 4. Fazemos asserts detalhados nos campos criados pelo .builder()
        assertNotNull(productCriadoInternamente);
        assertEquals(request.getName(), productCriadoInternamente.getName(), "O nome do produto deve ser igual ao do request");
        assertEquals(request.getValue(), productCriadoInternamente.getValue(), "O valor do produto deve ser igual ao do request");
    }
}