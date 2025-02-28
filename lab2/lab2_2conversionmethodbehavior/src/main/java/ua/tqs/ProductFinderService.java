package ua.tqs;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Optional;

public class ProductFinderService {

    public static final String API_PRODUCTS = "https://fakestoreapi.com/products";

    private final ISimpleHttpClient httpClient;

    // Constructor injection of the HTTP client
    public ProductFinderService(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<Product> findProductDetails(int productId) {
        String url = API_PRODUCTS + "/" + productId;
        String responseJson = httpClient.doHttpGet(url);

        if (responseJson == null || responseJson.isBlank()) {
            return Optional.empty();
        }

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(responseJson);

            if (!jsonObj.containsKey("id")) {
                return Optional.empty();
            }

            Product p = new Product();
            p.setId(((Long) jsonObj.get("id")).intValue());
            p.setTitle((String) jsonObj.get("title"));


            return Optional.of(p);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

}

