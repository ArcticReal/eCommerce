package com.skytala.eCommerce.domain.product.relations.product.event.review;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.review.ProductReview;
public class ProductReviewFound implements Event{

	private List<ProductReview> productReviews;

	public ProductReviewFound(List<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}

	public List<ProductReview> getProductReviews()	{
		return productReviews;
	}

}
