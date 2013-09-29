package it.lm;

import it.lm.product.Product;
import it.lm.product.Type;
import it.lm.tax.BasicTax;
import it.lm.tax.TaxResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@RunWith(JUnit4.class)
public class BasicTaxTest {
    final static Product chocolate = new Product(1, Type.Food, "Chocolate", 12.49d, false);
    final static Product cd = new Product(1, Type.Other, "music CD", 14.99d, false);

    @Test
    public void basicTaxOnChocolate() {
        final TaxResult res = BasicTax.instance().apply(chocolate);
        Assert.assertEquals(res.getOriginalPrice(), valueOf(12.49d));
        Assert.assertEquals(res.getTaxedPrice(), valueOf(12.49d));
        Assert.assertEquals(res.getTaxPayed(), BigDecimal.ZERO);
    }

    @Test
    public void basicTaxOnCD() {
        final TaxResult res = BasicTax.instance().apply(cd);
        Assert.assertEquals(res.getOriginalPrice(), valueOf(14.99d));
        Assert.assertEquals(res.getTaxedPrice(), valueOf(16.49d));
        Assert.assertEquals(res.getTaxPayed(), valueOf(1.50d));
    }
}
