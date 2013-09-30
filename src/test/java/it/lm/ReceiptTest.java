package it.lm;

import it.lm.product.Product;
import it.lm.product.Type;
import it.lm.tax.TaxResult;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static java.math.BigDecimal.valueOf;

@RunWith(JUnit4.class)
public class ReceiptTest {
    final static Product importedChocolate = new Product(1, Type.Food, "imported box of chocolates", 10.00d, true);
    final static Product cd = new Product(1, Type.Other, "music CD", 14.99d, false);

    @Test
    public void testTotalTax() throws Exception {
        final BigDecimal totalPayed = Receipt.totalTax(Arrays.asList(
                new TaxResult(cd, cd.getPrice().add(valueOf(1.50d)), valueOf(1.50d))
                , new TaxResult(importedChocolate, importedChocolate.getPrice().add(valueOf(2.00d)), valueOf(2.00d))
        ));
        Assert.assertEquals(BigDecimal.valueOf(3.50d), totalPayed);
    }

    @Test
    public void testTotalPayed() throws Exception {
        final BigDecimal totalPayed = Receipt.totalPayed(Arrays.asList(
                new TaxResult(cd, cd.getPrice().add(valueOf(1.50d)), valueOf(1.50d))
                , new TaxResult(importedChocolate, importedChocolate.getPrice().add(valueOf(2.00d)), valueOf(2.00d))
        ));
        Assert.assertEquals(BigDecimal.valueOf(28.49d).setScale(2), totalPayed);

    }
}
