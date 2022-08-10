package as.florenko.transfer.saver;

import as.florenko.transfer.inputData.TransferInfo;

import java.util.concurrent.ConcurrentHashMap;

public class Saver {
    private static ConcurrentHashMap<Integer, Saver> allTransfers = new ConcurrentHashMap<>();
    private final Integer numberOfOperation;
    private final Integer operationId;

    private final TransferInfo transferInfo;

    public Saver(Integer numberOfOperation, int operationId, TransferInfo transferInfo) {
        this.numberOfOperation = numberOfOperation;
        this.operationId = operationId;
        this.transferInfo = transferInfo;
        allTransfers.put(operationId, this);
    }
    public String getOperationId() {
        return String.valueOf(operationId);
    }

    public int getCommission() {
        return transferInfo.getAMOUNT().getVALUE() / 100;
    }

    public boolean getSaverByOperationId(Integer operationId) {
        return allTransfers.containsKey(operationId);
    }

    public String toString() {
        return "\nNUMBER_OF_OPERATION = " + numberOfOperation +
                "\nOPERATION_ID = " + getOperationId() +
                "\n<>TRANSFER_INFO<>" + "\n" + transferInfo.toString() +
                "\nCOMMISSION - > " + getCommission();
    }
}
