package it.lm.tax;

import it.lm.product.Product;

import java.math.BigDecimal;

public class TaxResult {
    final private Product originalProduct;
    final private BigDecimal taxedPrice;
    final private BigDecimal taxPayed;

    public TaxResult(Product originalProduct, BigDecimal taxedPrice, BigDecimal taxPayed) {
        this.originalProduct = originalProduct;
        this.taxedPrice = taxedPrice;
        this.taxPayed = taxPayed;
    }

    public static TaxResult zeroTaxResult(Product p) {
        return new TaxResult(p, p.getPrice(), BigDecimal.ZERO);
    }

    public Product getOriginalProduct() {
        return originalProduct;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public BigDecimal getTaxPayed() {
        return taxPayed;
    }

    public TaxResult sumTax(TaxResult other) {
        if(!originalProduct.equals(other.getOriginalProduct())) {
            throw new IllegalArgumentException("Summing taxes from different products");
        }
        final BigDecimal totalTaxPayed = getTaxPayed().add(other.getTaxPayed());
        return new TaxResult(getOriginalProduct()
                , getOriginalPrice().add(totalTaxPayed)
                , totalTaxPayed);
    }

    public BigDecimal getOriginalPrice() {
        return getOriginalProduct().getPrice();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("it.lm.tax.TaxResult{");
        sb.append("originalProduct=").append(originalProduct);
        sb.append(", taxedPrice=").append(taxedPrice);
        sb.append(", taxPayed=").append(taxPayed);
        sb.append('}');
        return sb.toString();
    }
}