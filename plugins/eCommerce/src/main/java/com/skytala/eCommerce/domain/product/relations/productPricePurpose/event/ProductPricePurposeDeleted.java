package com.skytala.eCommerce.domain.product.relations.productPricePurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPricePurpose.model.ProductPricePurpose;
public class ProductPricePurposeDeleted implements Event{

	private boolean success;

	public ProductPricePurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
