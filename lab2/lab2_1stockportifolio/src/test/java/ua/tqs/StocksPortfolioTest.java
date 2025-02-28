package ua.tqs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {
    @Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void testTotalValue() {

        //1. Prepare a mock to substitute the remote service (@Mock annotation)
        IStockmarketService market = mock(IStockmarketService.class);

        //2. Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
        StocksPortfolio portfolio = new StocksPortfolio(market);

        //3. Load the mock with the proper expectations (when...thenReturn)
        when( market.lookUpPrice("AMZN")).thenReturn(4.0);
        when( market.lookUpPrice("EUR")).thenReturn(1.5);
        when(market.lookUpPrice("AAPL")).thenReturn(3.0);
        when(market.lookUpPrice("TSLA")).thenReturn(10.0);
        when(market.lookUpPrice("GOOGL")).thenReturn(8.0);

        //4. Execute the test (use the service in the SuT)
        portfolio.addStock(new Stock("AMZN", 2));
        portfolio.addStock(new Stock("EUR", 4));
        portfolio.addStock(new Stock("AAPL", 3));
        portfolio.addStock(new Stock("TSLA", 1));
        portfolio.addStock(new Stock("GOOGL", 2));

        double result = portfolio.totalValue();

        assertEquals( 49.0, result);
        assertThat(result, is(49.0));
        assertThat(market.lookUpPrice("AMZN"), is(4.0));
    }

    @Test
    void testMostValuableStocks_NormalCase() {
        // Configurar preços fictícios no mock
        when(market.lookUpPrice("AMZN")).thenReturn(4.0);
        when(market.lookUpPrice("EUR")).thenReturn(1.5);
        when(market.lookUpPrice("AAPL")).thenReturn(3.0);
        when(market.lookUpPrice("TSLA")).thenReturn(10.0);
        when(market.lookUpPrice("GOOGL")).thenReturn(8.0);

        // Adicionar ações ao portfólio
        portfolio.addStock(new Stock("AMZN", 2));
        portfolio.addStock(new Stock("EUR", 4));
        portfolio.addStock(new Stock("AAPL", 3));
        portfolio.addStock(new Stock("TSLA", 1));
        portfolio.addStock(new Stock("GOOGL", 2));

        // Buscar as 3 ações mais valiosas
        List<Stock> topStocks = portfolio.mostValuableStocks(3);

        // Verificar se as 3 ações mais valiosas estão corretas
        assertEquals(3, topStocks.size());
        assertThat(topStocks.get(0).getLabel(), is("GOOGL"));
        assertThat(topStocks.get(1).getLabel(), is("TSLA"));
        assertThat(topStocks.get(2).getLabel(), is("AAPL"));
    }

    @Test
    void testMostValuableStocks_TopNGreaterThanAvailable() {
        // Adicionar apenas duas ações
        when(market.lookUpPrice("AMZN")).thenReturn(5.0);
        when(market.lookUpPrice("AAPL")).thenReturn(3.0);

        portfolio.addStock(new Stock("AMZN", 1));
        portfolio.addStock(new Stock("AAPL", 2));

        // Pedir mais ações do que existem
        List<Stock> topStocks = portfolio.mostValuableStocks(5);

        // Verificar se retorna todas as disponíveis (2)
        assertEquals(2, topStocks.size());
        assertThat(topStocks.get(0).getLabel(), is("AAPL"));
        assertThat(topStocks.get(1).getLabel(), is("AMZN"));
    }

    @Test
    void testMostValuableStocks_TopNZero() {
        // Testar com topN = 0
        List<Stock> topStocks = portfolio.mostValuableStocks(0);

        // Deve retornar uma lista vazia
        assertTrue(topStocks.isEmpty());
    }

    @Test
    void testMostValuableStocks_NoStocks() {
        // Testar sem nenhuma ação no portfólio
        List<Stock> topStocks = portfolio.mostValuableStocks(3);

        // Deve retornar uma lista vazia
        assertTrue(topStocks.isEmpty());
    }
}
