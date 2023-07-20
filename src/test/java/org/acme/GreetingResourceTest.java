package org.acme;

import com.fasterxml.jackson.jakarta.rs.yaml.YAMLMediaTypes;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }


    @Test
    public void justTag() throws IOException {
		String yamlPayload = new String(Files.readAllBytes(Paths.get("src/test/resources/tag.yaml")));

        EncoderConfig.encoderConfig().encodeContentTypeAs(YAMLMediaTypes.APPLICATION_JACKSON_YAML, ContentType.TEXT).defaultCharsetForContentType(YAMLMediaTypes.APPLICATION_JACKSON_YAML);

		Response response = 
        given()
          .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs(YAMLMediaTypes.APPLICATION_JACKSON_YAML, ContentType.TEXT)))
          .when()
                .log().all()
          .contentType(YAMLMediaTypes.APPLICATION_JACKSON_YAML)
		  .body(yamlPayload)
		  .post("/hello"); 

          response.then()
                  .log().all()
             .statusCode(200)
			 .contentType(ContentType.TEXT)
			 .body(containsString("one,two"));
    }

}
