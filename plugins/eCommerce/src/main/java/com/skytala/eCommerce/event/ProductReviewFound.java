package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.ProductReview;

public class ProductReviewFound implements Event {

	private List<ProductReview> productReviews;

	public ProductReviewFound(List<ProductReview> productReviews) {
		this.setProductReviews(productReviews);
	}

	public List<ProductReview> getProductReviews() {
		return productReviews;
	}

	public void setProductReviews(List<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}
}
