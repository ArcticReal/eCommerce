package com.skytala.eCommerce.domain.productReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productReview.model.ProductReview;
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
