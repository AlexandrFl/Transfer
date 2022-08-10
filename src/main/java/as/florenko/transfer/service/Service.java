package as.florenko.transfer.service;

import as.florenko.transfer.inputData.Confirm;
import as.florenko.transfer.inputData.TransferInfo;
import as.florenko.transfer.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class Service {

    @Autowired
     private Repository repository;

     public Service(Repository repository) {
         this.repository = repository;
     }

    public ResponseEntity<Object> makeTransfer(TransferInfo transfer) {
        return repository.makeTransfer(transfer);
    }

    public ResponseEntity<Object> confirmTransfer(Confirm confirm) {
        return repository.confirmTransfer(confirm);
    }
}
