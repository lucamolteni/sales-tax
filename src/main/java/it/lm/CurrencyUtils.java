package it.lm;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.valueOf;

public class CurrencyUtils {
    private static final BigDecimal APPROX_FACTOR = BigDecimal.valueOf(0.05d);
    private static final BigDecimal HUNDRED = valueOf(100);

    public static BigDecimal round(BigDecimal val) {
        return val.divide(APPROX_FACTOR).setScale(0, RoundingMode.HALF_UP).multiply(APPROX_FACTOR);
    }

    public static BigDecimal percentage(BigDecimal price, BigDecimal amount) {
        return price.divide(HUNDRED).multiply(amount);
    }
}
