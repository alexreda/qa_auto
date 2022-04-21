import api.ApiClient;
import api.BaseRequest;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
@Log
class LoginAndFilesTest {

    private static Response response;

    @BeforeAll
    static void precondition() {
        log.info("Precondition");
        BaseRequest.enableLogging();
          ApiClient.login();
    }

    @Test
    void test1() {
       log.info("Test1");
       Assertions.assertNotNull(ApiClient.getToken());
    }

    @Test
    void test2() {
        log.info("Test2");
        response = ApiClient.getRootFolder();
        Assertions.assertNotNull(response.getBody().jsonPath().get("items"));
    }
    @Test
    void test3() {
        log.info("Test3");
        response = ApiClient.getSpecificFolder("84c966d5-8dce-429d-8f92-44d5e28b1581");
        Assertions.assertEquals("Example_Datasets", response.getBody().jsonPath().get("name"));
    }
    @Test
    void test4() {
        log.info("Test4");
        response =ApiClient.countFilesInFolder("84c966d5-8dce-429d-8f92-44d5e28b1581");
        Assertions.assertSame(59, response.jsonPath().get("total"));
    }
}
