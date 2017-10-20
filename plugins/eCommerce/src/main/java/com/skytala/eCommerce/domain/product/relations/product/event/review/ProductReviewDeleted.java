package com.skytala.eCommerce.domain.product.relations.product.event.review;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.review.ProductReview;
public class ProductReviewDeleted implements Event{

	private boolean success;

	public ProductReviewDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
