package it.lm;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.valueOf;

public class CurrencyUtils {
    public static BigDecimal round(BigDecimal val) {
        final BigDecimal factor = BigDecimal.valueOf(0.05d);
        return val.divide(factor).setScale(0, RoundingMode.HALF_UP).multiply(factor);
    }

    public static BigDecimal percentage(BigDecimal price, BigDecimal amount) {
        return price.divide(valueOf(100)).multiply(amount);
    }
}
