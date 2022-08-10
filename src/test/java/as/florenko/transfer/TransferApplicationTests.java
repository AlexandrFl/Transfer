package as.florenko.transfer;

import as.florenko.transfer.inputData.Amount;
import as.florenko.transfer.inputData.Confirm;
import as.florenko.transfer.inputData.TransferInfo;
import as.florenko.transfer.repository.Repository;
import as.florenko.transfer.saver.Saver;
import as.florenko.transfer.service.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransferApplicationTests {
    private final Integer value = 133;
    private final String currency = "RUB";
    private final Amount amount = new Amount(value, currency);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Container
    public static final GenericContainer<?> transfer = new GenericContainer<>("s").withExposedPorts(8090);

    @BeforeAll
    public static void setUp() {
        transfer.start();
    }

    @Test
    void transferTest() {
        String cardFromCVV = "111";
        String cardFromNumber = "1111111111111111";
        String cardNumber = "1111111111111111";
        String cardFromValidTill = "11/45";
        TransferInfo transferInfo = new TransferInfo(cardFromCVV, cardNumber, cardFromValidTill, cardFromNumber, amount);
        ResponseEntity<Object> response = testRestTemplate.postForEntity("http://localhost:" + transfer.getMappedPort(8090) + "/transfer", transferInfo, Object.class);
        System.out.println(response);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
