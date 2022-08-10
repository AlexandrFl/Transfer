package as.florenko.transfer.controller;

import as.florenko.transfer.repository.Response;
import as.florenko.transfer.inputData.Confirm;
import as.florenko.transfer.inputData.TransferInfo;
import as.florenko.transfer.logger.Logger;
import as.florenko.transfer.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/")
public class Controller {

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping(path = "/transfer", consumes = {"application/json"})
    public ResponseEntity<Object> makeTransfer(@Valid @RequestBody TransferInfo transfer) {
        if (transfer.getCARD_FROM_NUMBER().equals(transfer.getCARD_TO_NUMBER())) {
            return new Response().generateWrongResponse("НОМЕРА КАРТЫ СОВПАДАЮТ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return service.makeTransfer(transfer);
    }

    @PostMapping(value = "/confirmOperation", consumes = {"application/json"})
    public ResponseEntity<Object> confirmTransfer(@RequestBody Confirm confirm) {
        return service.confirmTransfer(confirm);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> notValid(MethodArgumentNotValidException e) {
        Logger logger = new Logger();
        logger.log(String.valueOf(HttpStatus.BAD_REQUEST));
        return new Response().generateWrongResponse("Введены неверные данные карт", HttpStatus.BAD_REQUEST);
    }
}
