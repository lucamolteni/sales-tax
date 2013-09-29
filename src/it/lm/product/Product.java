package it.lm.product;

import java.math.BigDecimal;

public class Product {
    final long quantity;
    final Type type;
    final String name;
    final BigDecimal price;
    final boolean imported;

    public Product(long quantity, Type type, String name, boolean imported) {
        this(quantity, type, name, 0d, imported); // Free product! yay!
    }

    public Product(long quantity, Type type, String name, Double price, boolean imported) {
        this.quantity = quantity;
        this.type = type;
        if(name == null || "".equals(name)) {
            throw new IllegalArgumentException("Name of the product required");
        }
        this.name = name;
        this.price = BigDecimal.valueOf(price).setScale(2);
        this.imported = imported;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }

    public boolean isImported() {
        return imported;
    }

    public boolean isTaxable() {
        return type.equals(Type.Other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (imported != product.imported) return false;
        if (quantity != product.quantity) return false;
        if (!name.equals(product.name)) return false;
        if (!price.equals(product.price)) return false;
        if (type != product.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + type.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (imported ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("quantity=").append(quantity);
        sb.append(", type=").append(type);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", imported=").append(imported);
        sb.append('}');
        return sb.toString();
    }
}
