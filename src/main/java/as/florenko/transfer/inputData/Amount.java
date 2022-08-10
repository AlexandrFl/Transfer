package as.florenko.transfer.inputData;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class Amount {
    @Min(1)
    private final Integer VALUE;
    @NotNull
    private final String CURRENCY;

    public Amount(Integer value, String currency) {
        this.VALUE = value;
        this.CURRENCY = currency;
    }

    public Integer getVALUE() {
        return VALUE;
    }

    @Override
    public String toString() {
        return "VALUE -> " + VALUE +
                "\nCURRENCY -> " + CURRENCY;
    }
}
