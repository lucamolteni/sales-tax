package it.lm.tax;

import it.lm.product.Product;

import java.math.BigDecimal;

import static it.lm.CurrencyUtils.percentage;
import static it.lm.CurrencyUtils.round;

public class ImportTax implements Tax {
    final private static ImportTax instance = new ImportTax();
    final private static BigDecimal percValue = BigDecimal.valueOf(5d);

    private ImportTax() {

    }

    public TaxResult apply(Product p) {
        final BigDecimal price = p.getPrice();
        if(p.isImported()) {
            final BigDecimal taxPayed = round(percentage(price, percValue));
            return new TaxResult(p, price.add(taxPayed), taxPayed);
        }
        return new TaxResult(p, price, BigDecimal.ZERO);
    }

    public static Tax instance() {
        return instance;
    }


}
