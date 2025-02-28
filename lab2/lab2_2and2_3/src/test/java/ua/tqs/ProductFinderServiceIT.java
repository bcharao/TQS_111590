package ua.tqs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Optional;

public class ProductFinderServiceIT {

    @Test
    public void testIntegrationFindProductDetails() throws IOException {
        // Cria o client real (TqsBasicHttpClient) que faz a chamada HTTP
        ua.tqs.RealHttpClient realClient = new RealHttpClient();
        ProductFinderService service = new ProductFinderService(realClient);

        // Chama a API real para o produto com ID 1
        Optional<Product> productOpt = service.findProductDetails(1);
        assertTrue(productOpt.isPresent(), "O produto não foi encontrado na API real.");

        Product product = productOpt.get();
        assertEquals(1, product.getId());
        assertNotNull(product.getTitle());
        System.out.println("Produto retornado: " + product);
    }
}