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

        ProductDetailsDTO dto = new ProductDetailsDTO(product);

        for(ProductPrice productPrice : prices)
            if(productPrice.getProductId().equals(product.getProductId()))
                dto.setPrice(productPrice.getPrice());

        for(ProductAttribute attribute : attributes)
            if(attribute.getProductId().equals(product.getProductId())){
                switch (attribute.getAttrName()){
                    case ProductAttributes.AUTHOR:
                        dto.setAuthor(attribute.getAttrValue());
                        break;
                    case ProductAttributes.ISBN:
                        dto.setISBN(attribute.getAttrValue());
                        break;
                    case ProductAttributes.PUBLISHER:
                        dto.setPublisher(attribute.getAttrValue());

                }

            }

        return dto;


    }


    public ProductDetailsDTO(Product product) {

        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.longDescription = product.getLongDescription();
        this.mediumImageUrl = product.getMediumImageUrl();
    }

    public ProductDetailsDTO(){

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
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        currentDate.setNanos(0);

        pp.setProductId(productId);
        pp.setPrice(price);
        pp.setCurrencyUomId("EUR");
        pp.setFromDate(currentDate);
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
        ISBN.setAttrValue(getISBN());
        attributes.add(ISBN);

        ProductAttribute publisher = new ProductAttribute();
        publisher.setProductId(getProductId());
        publisher.setAttrName(ProductAttributes.PUBLISHER);
        publisher.setAttrValue(getPublisher());
        attributes.add(publisher);

        return attributes;
    }
}
