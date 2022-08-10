package as.florenko.transfer.repository;

import as.florenko.transfer.logger.Logger;
import as.florenko.transfer.inputData.Confirm;
import as.florenko.transfer.inputData.TransferInfo;
import as.florenko.transfer.saver.Saver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.atomic.AtomicInteger;

public class Repository {

    private final Logger logger = new Logger();
    private static AtomicInteger operationId = new AtomicInteger(1);
    private static AtomicInteger numberOfOperation = new AtomicInteger(1);

    private Saver saver;
    public ResponseEntity<Object> makeTransfer(TransferInfo transfer) {
        saver = new Saver(numberOfOperation.getAndIncrement(), operationId.get(), transfer);
        logger.log(saver.toString());
        logger.log("Transfer status - " + HttpStatus.OK);
        return new Response().generateOkResponse(operationId.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> confirmTransfer(Confirm confirm) {
        if (!saver.getSaverByOperationId(Integer.parseInt(confirm.getOperationId()))) {
            logger.log("Confirming status - " + HttpStatus.INTERNAL_SERVER_ERROR);
            return new Response().generateWrongResponse("Ошибка подтверждения операции", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.log("Confirming status - " + HttpStatus.OK);
        return new Response().generateOkResponse(operationId.getAndIncrement(), HttpStatus.OK);
    }
}
