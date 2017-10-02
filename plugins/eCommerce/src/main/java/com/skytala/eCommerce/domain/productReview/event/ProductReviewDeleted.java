package com.skytala.eCommerce.domain.productReview.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productReview.model.ProductReview;
public class ProductReviewDeleted implements Event{

	private boolean success;

	public ProductReviewDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
