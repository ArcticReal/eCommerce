package com.skytala.eCommerce.domain.product.relations.product.dto.category;

import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;

public class CategoryListItemDTO {
    private String productCategoryId;
    private String categoryName;
    private String description;

    public CategoryListItemDTO(ProductCategory category) {
        productCategoryId = category.getProductCategoryId();
        categoryName = category.getCategoryName();
        description = category.getDescription();
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
