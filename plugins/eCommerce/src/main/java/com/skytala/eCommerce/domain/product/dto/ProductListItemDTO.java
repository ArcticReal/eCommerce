package com.skytala.eCommerce.domain.product.dto;

import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;

import java.math.BigDecimal;
import java.util.List;

public class ProductListItemDTO {

    private String productId;
    private String productName;
    private String mediumImageUrl;
    private String author;
    private BigDecimal price;

    public static ProductListItemDTO create(Product product,
                                            List<ProductPrice> prices,
                                            List<ProductAttribute> authors) {

        ProductListItemDTO dto = new ProductListItemDTO(product);

        for(ProductPrice productPrice : prices)
            if(productPrice.getProductId().equals(product.getProductId()))
                dto.setPrice(productPrice.getPrice());

        for(ProductAttribute author : authors)
            if(author.getProductId().equals(product.getProductId()))
                dto.setAuthor(author.getAttrValue());

        return dto;
    }

    public ProductListItemDTO(Product p) {
        this.productName = p.getProductName();
        this.productId = p.getProductId();
        this.mediumImageUrl = p.getMediumImageUrl();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
}
