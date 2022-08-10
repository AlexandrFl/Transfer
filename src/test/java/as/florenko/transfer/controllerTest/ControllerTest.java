package as.florenko.transfer.controllerTest;

import as.florenko.transfer.inputData.Confirm;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import as.florenko.transfer.controller.Controller;
import as.florenko.transfer.inputData.Amount;
import as.florenko.transfer.inputData.TransferInfo;
import as.florenko.transfer.repository.Repository;
import as.florenko.transfer.service.Service;
import org.mockito.Mockito;

import java.util.Date;


public class ControllerTest {

    private final Integer value = 133;
    private final String currency = "RUB";
    private final String cardFromCVV = "111";
    private final String cardFromNumber = "1111111111111111";
    private final String cardToNumber = "1111111116111111";
    private final String cardFromValidTill = "11/45";
    private final Amount amount = new Amount(value, currency);
    private final TransferInfo transferInfo = new TransferInfo(cardFromCVV, cardFromNumber, cardFromValidTill, cardToNumber, amount);
    private static final Date DATE = new Date();

    @BeforeAll
    public static void start() {
        System.out.println("START TESTING" + " " + DATE);
    }

    @AfterAll
    public static void end() {
        System.out.println("END TESTING" + " " + DATE);
    }

    @Test
    void confirmingTest() {
        String okId = "1";
        String wrongId = "2";

        Confirm okConfirm = Mockito.mock(Confirm.class);
        Mockito.when(okConfirm.getOperationId()).thenReturn(okId);

        Confirm wrongConfirm = Mockito.mock(Confirm.class);
        Mockito.when(wrongConfirm.getOperationId()).thenReturn(wrongId);

        Repository repository = new Repository();
        Service service = new Service(repository);
        Controller controller = new Controller(service);

        controller.makeTransfer(transferInfo);
        controller.confirmTransfer(okConfirm);
        System.out.println();
        controller.confirmTransfer(wrongConfirm);
    }
}
