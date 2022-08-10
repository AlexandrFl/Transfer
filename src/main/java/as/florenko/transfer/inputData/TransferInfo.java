package as.florenko.transfer.inputData;

import as.florenko.transfer.validator.CardFromValidTill;
import javax.validation.Valid;
import javax.validation.constraints.Size;


public class TransferInfo {
    @Size (min = 3, max = 3)
    private final String CARD_FROM_CVV;
    @Size(min = 16, max = 16)
    private final String CARD_FROM_NUMBER;
    @CardFromValidTill
    private final String CARD_FROM_VALID_TILL;
    @Size(min = 16, max = 16)
    private final String CARD_TO_NUMBER;
    @Valid
    private final Amount AMOUNT;

    public TransferInfo(String cardFromCVV, String cardFromNumber, String cardFromValidTill, String cardToNumber, Amount amount) {
        this.CARD_FROM_CVV = cardFromCVV;
        this.CARD_FROM_NUMBER = cardFromNumber;
        this.CARD_FROM_VALID_TILL = cardFromValidTill;
        this.CARD_TO_NUMBER = cardToNumber;
        this.AMOUNT = amount;
    }
    public Amount getAMOUNT() {
        return AMOUNT;
    }

    public String getCARD_FROM_NUMBER() {
        return CARD_FROM_NUMBER;
    }

    public String getCARD_TO_NUMBER() {
        return CARD_TO_NUMBER;
    }

    @Override
    public String toString() {
        return "CARD_FROM_NUMBER -> " + CARD_FROM_NUMBER +
                "\nCARD_FROM_VALID_TILL -> " + CARD_FROM_VALID_TILL +
                "\nCARD_FROM_CVV -> " + CARD_FROM_CVV +
                "\nCARD_TO_NUMBER -> " + CARD_TO_NUMBER +
                "\nAMOUNT:" + AMOUNT.toString();
    }
}
