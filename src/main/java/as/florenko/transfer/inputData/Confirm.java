package as.florenko.transfer.inputData;

public class Confirm {
    private String operationId;
    private String code;

    public Confirm(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }
}
