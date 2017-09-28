package com.skytala.eCommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

public class ProductReview implements Serializable {

	private static final long serialVersionUID = 1L;
	private String productReviewId;
	private String productStoreId;
	private String productId;
	private String userLoginId;
	private String statusId;
	private Boolean postedAnonymous;
	private Timestamp postedDateTime;
	private BigDecimal productRating;
	private String productReview;

	public String getProductReviewId() {
		return productReviewId;
	}

	public void setProductReviewId(String productReviewId) {
		this.productReviewId = productReviewId;
	}

	public String getProductStoreId() {
		return productStoreId;
	}

	public void setProductStoreId(String productStoreId) {
		this.productStoreId = productStoreId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public Boolean getPostedAnonymous() {
		return postedAnonymous;
	}

	public void setPostedAnonymous(Boolean postedAnonymous) {
		this.postedAnonymous = postedAnonymous;
	}

	public Timestamp getPostedDateTime() {
		return postedDateTime;
	}

	public void setPostedDateTime(Timestamp postedDateTime) {
		this.postedDateTime = postedDateTime;
	}

	public BigDecimal getProductRating() {
		return productRating;
	}

	public void setProductRating(BigDecimal productRating) {
		this.productRating = productRating;
	}

	public String getProductReview() {
		return productReview;
	}

	public void setProductReview(String productReview) {
		this.productReview = productReview;
	}

	public Map<String, Object> mapAttributeField() {
		return ProductReviewMapper.map(this);
	}
}
