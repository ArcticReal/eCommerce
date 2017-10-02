package com.skytala.eCommerce.domain.productReview.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productReview.model.ProductReview;
public class ProductReviewFound implements Event{

	private List<ProductReview> productReviews;

	public ProductReviewFound(List<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}

	public List<ProductReview> getProductReviews()	{
		return productReviews;
	}

}
