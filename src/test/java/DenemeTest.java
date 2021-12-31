import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DenemeTest {



    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    List<Integer> integerDataList = new ArrayList<>();
    HashSet<Integer> integerDataSet = new HashSet<>();
    List<String> stringDataList = new ArrayList<>();
    HashSet<String> stringDataSet = new HashSet<>();





   @org.junit.Test
    public void test() {
        System.out.println("hello world");

    }





    @org.junit.Test
        public void getResponse(){
        response = given().accept(ContentType.JSON).auth().oauth2("b529970a2c6a408c4d86903d20f1bff0b682e74df198f6a720c986b67175b24e").when().get(endPoint);
            Assert.assertEquals(response.getStatusCode(),200);
    }
}
