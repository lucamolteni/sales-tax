package it.lm.tax;

import it.lm.product.Product;

import java.math.BigDecimal;

import static it.lm.CurrencyUtils.percentage;
import static it.lm.CurrencyUtils.round;

public class BasicTax implements Tax {
    final private static BasicTax instance = new BasicTax();
    final private static BigDecimal percValue = BigDecimal.valueOf(10d);

    private BasicTax() {

    }

    public TaxResult apply(Product p) {
        final BigDecimal price = p.getPrice();
        if (p.isTaxable()) {
            final BigDecimal taxPayed = round(percentage(price, percValue));
            return new TaxResult(p, price.add(taxPayed), taxPayed);
        }
        return new TaxResult(p, price, BigDecimal.ZERO);
    }

    public static Tax instance() {
        return instance;
    }
}
