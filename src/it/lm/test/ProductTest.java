package it.lm.test;

import it.lm.product.Product;
import it.lm.product.Type;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProductTest {
    @Test
    public void foodShouldNotBeTaxable() {
        final Product p = new Product(1, Type.Food, "chocolate bar", Boolean.FALSE);
        Assert.assertFalse(p.isTaxable());
    }

    @Test
    public void bookShouldNotBeTaxable() {
        final Product p = new Product(1, Type.Book, "Book", Boolean.FALSE);
        Assert.assertFalse(p.isTaxable());
    }

    @Test
    public void mediceShouldNotBeTaxable() {
        final Product p = new Product(1, Type.Book, "Book", Boolean.FALSE);
        Assert.assertFalse(p.isTaxable());
    }

    @Test
    public void cdShouldBeTaxable() {
        final Product p = new Product(1, Type.Other, "music CD Led Zeppelin 1", Boolean.FALSE);
        Assert.assertTrue(p.isTaxable());
    }
}
