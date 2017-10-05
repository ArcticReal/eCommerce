package com.skytala.eCommerce.domain.productPricePurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPricePurpose.model.ProductPricePurpose;
public class ProductPricePurposeDeleted implements Event{

	private boolean success;

	public ProductPricePurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
