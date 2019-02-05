package managers.loggedInformationManager.Currency;

import java.math.BigDecimal;

public class CurrencyObject {
    private String name;
    private String shortcut;
    private BigDecimal value;

    public CurrencyObject(String name, String shortcut, BigDecimal value) {
        this.name = name;
        this.shortcut = shortcut;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public BigDecimal getValue() {
        return value;
    }
}
