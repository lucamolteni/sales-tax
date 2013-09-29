package it.lm;

import it.lm.product.Product;
import it.lm.product.Type;
import it.lm.tax.BasicTax;
import it.lm.tax.ImportTax;
import it.lm.tax.Tax;
import it.lm.tax.TaxResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Receipt {

    private static final List<Tax> taxes = Arrays.asList(BasicTax.instance(), ImportTax.instance());

    public static void printReceipt(Collection<Product> products, List<Tax> taxes) {
        final Collection<TaxResult> taxesApplied = new ArrayList<TaxResult>();
        for (Product p : products) {
            TaxResult res = TaxResult.zeroTaxResult(p);
            for(Tax t : taxes) {
                res = res.sumTax(t.apply(p));
            }
            taxesApplied.add(res);
        }

        for (TaxResult r : taxesApplied) {
            final Product p = r.getOriginalProduct();
            System.out.printf("%d %s: %.2f\n", p.getQuantity(), p.getName(), r.getTaxedPrice());
        }

        System.out.printf("Sales Taxes: %.2f\n", totalTax(taxesApplied));
        System.out.printf("Total: %.2f\n\n", totalPayed(taxesApplied));

    }

    public static BigDecimal totalTax(Collection<TaxResult> taxes) {
        BigDecimal aggr = BigDecimal.ZERO;
        for (TaxResult tr : taxes) {
            aggr = aggr.add(tr.getTaxPayed());
        }
        return aggr;
    }

    public static BigDecimal totalPayed(Collection<TaxResult> taxes) {
        BigDecimal aggr = BigDecimal.ZERO;
        for (TaxResult tr : taxes) {
            aggr = aggr.add(tr.getTaxedPrice());
        }
        return aggr;
    }

    public static void main(String[] args) {
        input1();
        input2();
        input3();
    }

    private static void input1() {
        printReceipt(
                Arrays.asList(
                        new Product(1, Type.Book, "book", 12.49d, false)
                        , new Product(1, Type.Other, "music CD", 14.99d, false)
                        , new Product(1, Type.Food, "chocolate bar", 0.85d, false)

                ), taxes
        );
    }

    private static void input2() {
        printReceipt(
                Arrays.asList(
                        new Product(1, Type.Food, "imported box of chocolates", 10.00d, true)
                        , new Product(1, Type.Other, "imported bottle of perfume", 47.50d, true)

                ), taxes
        );
    }

    private static void input3() {
        printReceipt(
                Arrays.asList(
                         new Product(1, Type.Other, "imported bottle of perfume", 27.99d, true)
                        , new Product(1, Type.Other, "bottle of perfume", 18.99d, false)
                        , new Product(1, Type.Medical, "packet of headache pills", 9.75d, false)
                        , new Product(1, Type.Food, "imported box of chocolates", 11.25d, true)

                ), taxes
        );
    }
}
