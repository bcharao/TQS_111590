package ua.tqs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StocksPortfolio {
    private IStockmarketService stockmarket;
    private List<Stock> stocks;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
        this.stocks = new ArrayList<>();
    }
    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double totalValue() {
        double totalPrice = 0;
        for (Stock stock : stocks) {
                totalPrice+= stockmarket.lookUpPrice(stock.getLabel()) *
                stock.getQuantity();
        }
        return totalPrice;
    }
    /**
     * Returns the top N most valuable stocks in the portfolio.
     *
     * @param topN the number of most valuable stocks to return
     * @return a list with the topN most valuable stocks in the portfolio
     */
    public List<Stock> mostValuableStocks(int topN) {
        return stocks.stream()
                .sorted(Comparator.comparingDouble((Stock stock) ->
                                stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity())
                        .reversed()) // Ordena do maior para o menor
                .limit(topN) // Pega os top N mais valiosos
                .collect(Collectors.toList());
    }
 }
