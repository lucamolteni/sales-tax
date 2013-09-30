package it.lm;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

@RunWith(JUnit4.class)
public class CurrencyUtilsTest {
    @Test
    public void testRound() throws Exception {
        final BigDecimal rounded = CurrencyUtils.round(BigDecimal.valueOf(0.56d).setScale(2));
        Assert.assertEquals(BigDecimal.valueOf(0.55d).setScale(2), rounded);
    }

    @Test
    public void testPercentage() throws Exception {
        final BigDecimal perc = CurrencyUtils.percentage(BigDecimal.valueOf(100), BigDecimal.valueOf(5)).setScale(2);
        Assert.assertEquals(BigDecimal.valueOf(5.00d).setScale(2), perc);
    }

    @Test
    public void testPercentageDecimal() throws Exception {
        final BigDecimal perc = CurrencyUtils.percentage(BigDecimal.valueOf(11.25d), BigDecimal.valueOf(5));
        Assert.assertEquals(BigDecimal.valueOf(0.5625d), perc);
    }
}
