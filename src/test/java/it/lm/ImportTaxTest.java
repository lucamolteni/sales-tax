package it.lm;

import it.lm.product.Product;
import it.lm.product.Type;
import it.lm.tax.BasicTax;
import it.lm.tax.ImportTax;
import it.lm.tax.TaxResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@RunWith(JUnit4.class)
public class ImportTaxTest {
    final static Product importedChocolate = new Product(1, Type.Food, "imported box of chocolates", 10.00d, true);
    final static Product cd = new Product(1, Type.Other, "music CD", 14.99d, false);

    @Test
    public void importedTaxOnCDNotImported() {
        final TaxResult res = ImportTax.instance().apply(cd);
        Assert.assertEquals(res.getOriginalPrice(), valueOf(14.99d));
        Assert.assertEquals(res.getTaxedPrice(), valueOf(14.99d));
        Assert.assertEquals(res.getTaxPayed(), BigDecimal.ZERO);
    }

    @Test
    public void importedTaxOnPerfumeImported() {
        final TaxResult res = ImportTax.instance().apply(importedChocolate);
        Assert.assertEquals(res.getOriginalPrice(), valueOf(10.00d).setScale(2));
        Assert.assertEquals(res.getTaxedPrice(), valueOf(10.50d).setScale(2));
        Assert.assertEquals(res.getTaxPayed(), valueOf(0.50d).setScale(2));
    }
}
