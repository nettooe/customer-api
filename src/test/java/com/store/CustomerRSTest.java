package com.store;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;

@QuarkusTest
public class CustomerRSTest {

	@Test
	@TestSecurity(user = "user", roles = {"admin", "user"})
    public void test_getCustomerById() {
        given()
                .when().get("/customer/{id}", 1)
                .then()
                .statusCode(403);
//                .body("$.size()", is(2),
//                        "name", containsInAnyOrder("Apple", "Pineapple"),
//                        "description", containsInAnyOrder("Winter fruit", "Tropical fruit"));
    }

    @Test
    @TestSecurity(user = "user", roles = {"admin", "user"})
    public void test_save() {
        given()
                .body("{\"address\": \"SaoPaulo\", \"cpf\": \"78945612399\", \"name\": \"Leonardo DaVinci\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("accept", "*/*")
                .when()
                .post("/customer")
                .then()
                .statusCode(403);
//                .body("$.size()", is(0),
//                        "name", containsInAnyOrder("Apple", "Pineapple", "Pear"),
//                        "description", containsInAnyOrder("Winter fruit", "Tropical fruit", "Winter fruit"));

//        given()
//                .body("{\"name\": \"Pear\", \"description\": \"Winter fruit\"}")
//                .header("Content-Type", MediaType.APPLICATION_JSON)
//                .when()
//                .delete("/fruits")
//                .then()
//                .statusCode(200)
//                .body("$.size()", is(2),
//                        "name", containsInAnyOrder("Apple", "Pineapple"),
//                        "description", containsInAnyOrder("Winter fruit", "Tropical fruit"));
    }

}