package com.skytala.eCommerce.domain.cart.dto;

import com.skytala.eCommerce.domain.cart.Position;
import com.skytala.eCommerce.domain.product.dto.ProductDetailsDTO;
import com.skytala.eCommerce.domain.product.dto.ProductListItemDTO;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartItemDTO {

    private String productId;
    private String productName;
    private String mediumImageUrl;
    private String author;
    private BigDecimal price;
    private BigDecimal count;



    public static ShoppingCartItemDTO create(ProductDetailsDTO details,
                                             BigDecimal count){


        ShoppingCartItemDTO dto = new ShoppingCartItemDTO(count);
        dto.setProductId(details.getProductId());
        dto.setProductName(details.getProductName());
        dto.setMediumImageUrl(details.getMediumImageUrl());
        dto.setAuthor(details.getAuthor());
        dto.setPrice(details.getPrice());

        return dto;

    }


    public ShoppingCartItemDTO(BigDecimal count){
        this.count = count;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
