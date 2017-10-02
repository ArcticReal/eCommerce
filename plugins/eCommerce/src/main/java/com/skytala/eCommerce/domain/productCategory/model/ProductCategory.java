package com.skytala.eCommerce.domain.productCategory.model;

import java.io.Serializable;
import java.util.Map;

import com.skytala.eCommerce.domain.productCategory.mapper.ProductCategoryMapper;

public class ProductCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	private String productCategoryId;
	private String productCategoryTypeId;
	private String primaryParentCategoryId;
	private String categoryName;
	private String description;
	private String longDescription;
	private String categoryImageUrl;
	private String linkOneImageUrl;
	private String linkTwoImageUrl;
	private String detailScreen;
	private Boolean showInSelect;

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryTypeId() {
		return productCategoryTypeId;
	}

	public void setProductCategoryTypeId(String productCategoryTypeId) {
		this.productCategoryTypeId = productCategoryTypeId;
	}

	public String getPrimaryParentCategoryId() {
		return primaryParentCategoryId;
	}

	public void setPrimaryParentCategoryId(String primaryParentCategoryId) {
		this.primaryParentCategoryId = primaryParentCategoryId;
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

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getCategoryImageUrl() {
		return categoryImageUrl;
	}

	public void setCategoryImageUrl(String categoryImageUrl) {
		this.categoryImageUrl = categoryImageUrl;
	}

	public String getLinkOneImageUrl() {
		return linkOneImageUrl;
	}

	public void setLinkOneImageUrl(String linkOneImageUrl) {
		this.linkOneImageUrl = linkOneImageUrl;
	}

	public String getLinkTwoImageUrl() {
		return linkTwoImageUrl;
	}

	public void setLinkTwoImageUrl(String linkTwoImageUrl) {
		this.linkTwoImageUrl = linkTwoImageUrl;
	}

	public String getDetailScreen() {
		return detailScreen;
	}

	public void setDetailScreen(String detailScreen) {
		this.detailScreen = detailScreen;
	}

	public Boolean getShowInSelect() {
		return showInSelect;
	}

	public void setShowInSelect(Boolean showInSelect) {
		this.showInSelect = showInSelect;
	}

	public Map<String, Object> mapAttributeField() {
		return ProductCategoryMapper.map(this);
	}
}
