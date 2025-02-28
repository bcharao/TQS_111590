package ua.tqs;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Optional;

class ProductFinderServiceTest {

    @Test
    void whenValidId_thenReturnProduct() throws IOException {
        // 1. Mock
        ISimpleHttpClient mockClient = mock(ISimpleHttpClient.class);
        ProductFinderService service = new ProductFinderService(mockClient);

        // 2. Fake JSON para o ID=3
        String fakeJson = "{ \"id\": 3, \"title\": \"Mens Cotton Jacket\" }";
        when(mockClient.doHttpGet("https://fakestoreapi.com/products/3"))
                .thenReturn(fakeJson);

        // 3. Invocar
        Optional<Product> productOpt = service.findProductDetails(3);

        // 4. Verificar
        assertTrue(productOpt.isPresent());
        assertEquals(3, productOpt.get().getId());
        assertEquals("Mens Cotton Jacket", productOpt.get().getTitle());
        verify(mockClient).doHttpGet("https://fakestoreapi.com/products/3");
    }

    @Test
    void whenInvalidId_thenReturnEmpty() throws IOException {
        // 1. Mock
        ISimpleHttpClient mockClient = mock(ISimpleHttpClient.class);
        ProductFinderService service = new ProductFinderService(mockClient);

        // 2. Fake JSON (pode simular erro, ou JSON vazio)
        //    Ou, se preferir, retorne null ou algo que faça seu parse falhar
        when(mockClient.doHttpGet("https://fakestoreapi.com/products/300"))
                .thenReturn(""); // Exemplo: resposta vazia ou JSON de erro

        // 3. Invocar
        Optional<Product> productOpt = service.findProductDetails(300);

        // 4. Verificar
        assertFalse(productOpt.isPresent());
        verify(mockClient).doHttpGet("https://fakestoreapi.com/products/300");
    }
}
