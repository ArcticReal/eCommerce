package com.skytala.eCommerce.domain.product.dto;

import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;

import java.math.BigDecimal;
import java.util.List;

public class ProductDetailsDTO {

    private String productId;
    private String productName;
    private String description;
    private String longDescription;
    private String mediumImageUrl;

    //non-product Attributes
    private BigDecimal price;
    private String ISBN;
    private String author;
    private String publisher;

    /*
        factory method
     */
    public static ProductDetailsDTO create(Product product,
                                           List<ProductPrice> prices,
                                           List<ProductAttribute> attributes){

        


        return null;//TODO

    }


    public ProductDetailsDTO(Product product) {

        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.longDescription = product.getLongDescription();
        this.mediumImageUrl = product.getMediumImageUrl();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
