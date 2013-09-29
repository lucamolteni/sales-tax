package it.lm.tax;

import it.lm.product.Product;

public interface Tax {
    public TaxResult apply(Product p);
}
