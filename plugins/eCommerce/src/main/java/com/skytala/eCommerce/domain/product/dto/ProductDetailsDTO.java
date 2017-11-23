package com.skytala.eCommerce.domain.product.dto;

import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
import com.skytala.eCommerce.domain.product.relations.product.model.price.ProductPrice;
import com.skytala.eCommerce.domain.product.util.ProductAttributes;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class ProductDetailsDTO {

    private String productId;
    private String productName;
    private String description;
    private String longDescription;
    private String mediumImageUrl;
    private BigDecimal productRating;

    //non-product Attributes
    private BigDecimal price;
    private String isbn;
    private String author;
    private String publisher;
    private String publishingDate;
    private List<String> categoryIds;


    /*
        factory methods
     */
    public static ProductDetailsDTO create(Product product,
                                           List<ProductPrice> prices,
                                           List<ProductAttribute> attributes){

        ProductDetailsDTO dto = new ProductDetailsDTO(product);

        for(ProductPrice productPrice : prices)
            if(productPrice.getProductId().equals(product.getProductId())){
                dto.setPrice(productPrice.getPrice());
            }

        for(ProductAttribute attribute : attributes)
            if(attribute.getProductId().equals(product.getProductId())){
                switch (attribute.getAttrName()){
                    case ProductAttributes.AUTHOR:
                        dto.setAuthor(attribute.getAttrValue());
                        break;
                    case ProductAttributes.ISBN:
                        dto.setIsbn(attribute.getAttrValue());
                        break;
                    case ProductAttributes.PUBLISHER:
                        dto.setPublisher(attribute.getAttrValue());
                        break;
                    case ProductAttributes.PUBLISHING_DATE:
                        dto.setPublishingDate(attribute.getAttrValue());
                        break;

                }

            }

        return dto;


    }

    public static ProductDetailsDTO create(Product product,
                                           List<ProductPrice> prices,
                                           List<ProductAttribute> attributes,
                                           List<String> categoryIds){

        ProductDetailsDTO dto = ProductDetailsDTO.create(product, prices, attributes);
        dto.setCategoryIds(categoryIds);

        return dto;
    }



    public ProductDetailsDTO(Product product) {

        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.longDescription = product.getLongDescription();
        this.mediumImageUrl = product.getMediumImageUrl();
        this.productRating = product.getProductRating();
    }

    public ProductDetailsDTO(){

    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }

    public BigDecimal getProductRating() {
        return productRating;
    }

    public void setProductRating(BigDecimal productRating) {
        this.productRating = productRating;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public Product extractProduct(){
        Product p = new Product();

        p.setProductId(productId);
        p.setProductName(productName);
        p.setDescription(description);
        p.setLongDescription(longDescription);
        p.setMediumImageUrl(mediumImageUrl);

        return p;
    }

    public ProductPrice extractProductPrice(){
        ProductPrice pp = new ProductPrice();

        pp.setProductId(productId);
        pp.setPrice(price);

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        currentDate.setNanos(0);
        pp.setFromDate(currentDate);

        pp.setCurrencyUomId("EUR");
        pp.setProductPriceTypeId("DEFAULT_PRICE");
        pp.setProductPricePurposeId("PURCHASE");
        pp.setProductStoreGroupId("SKYTALA_GROUP");
        return pp;
    }

    public List<ProductAttribute> extractAllAttributes(){
        List<ProductAttribute> attributes = new LinkedList<>();

        ProductAttribute author = new ProductAttribute();
        author.setProductId(getProductId());
        author.setAttrName(ProductAttributes.AUTHOR);
        author.setAttrValue(getAuthor());
        attributes.add(author);

        ProductAttribute ISBN = new ProductAttribute();
        ISBN.setProductId(getProductId());
        ISBN.setAttrName(ProductAttributes.ISBN);
        ISBN.setAttrValue(getIsbn());
        attributes.add(ISBN);

        ProductAttribute publisher = new ProductAttribute();
        publisher.setProductId(getProductId());
        publisher.setAttrName(ProductAttributes.PUBLISHER);
        publisher.setAttrValue(getPublisher());
        attributes.add(publisher);

        ProductAttribute publishingDate = new ProductAttribute();
        publishingDate.setProductId(getProductId());
        publishingDate.setAttrName(ProductAttributes.PUBLISHING_DATE);
        publishingDate.setAttrValue(getPublishingDate());
        attributes.add(publishingDate);

        return attributes;
    }
}
