package com.skytala.eCommerce.domain.cart.dto;

import com.skytala.eCommerce.domain.cart.Position;
import com.skytala.eCommerce.domain.product.dto.ProductListItemDTO;
import com.skytala.eCommerce.domain.product.model.Product;

import java.math.BigDecimal;

public class ShoppingCartItemDTO {

    private ProductListItemDTO product;
    private BigDecimal count;

    public ShoppingCartItemDTO(Position position) {
        this.product = new ProductListItemDTO(position.getProduct());
        this.count = position.getNumberProducts();
    }

    public ShoppingCartItemDTO(Product product, BigDecimal count){
        this.product = new ProductListItemDTO(product);
        this.count = count;
    }

    public ProductListItemDTO getProduct() {
        return product;
    }

    public void setProduct(ProductListItemDTO product) {
        this.product = product;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
