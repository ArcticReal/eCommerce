package com.skytala.eCommerce.domain.product.relations.product.event.review;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.review.ProductReview;
public class ProductReviewAdded implements Event{

	private ProductReview addedProductReview;
	private boolean success;

	public ProductReviewAdded(ProductReview addedProductReview, boolean success){
		this.addedProductReview = addedProductReview;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductReview getAddedProductReview() {
		return addedProductReview;
	}

}
