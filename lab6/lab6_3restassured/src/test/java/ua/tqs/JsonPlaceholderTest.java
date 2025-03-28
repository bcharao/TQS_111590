package ua.tqs;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonPlaceholderTest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    void testAllTodosAvailable() {
        given().
                when().
                get(BASE_URL + "/todos").
                then().
                statusCode(200);
    }

    @Test
    void testTodo4HasCorrectTitle() {
        given().
                when().
                get(BASE_URL + "/todos/4").
                then().
                statusCode(200).
                body("title", equalTo("et porro tempora"));
    }

    @Test
    void testTodosContain198And199() {
        given().
                when().
                get(BASE_URL + "/todos").
                then().
                statusCode(200).
                body("id", hasItems(198, 199));
    }

    @Test
    void testTodosRespondInLessThan2Seconds() {
        given().
                when().
                get(BASE_URL + "/todos").
                then().
                time(lessThan(2000L)); // tempo em milissegundos
    }
}
