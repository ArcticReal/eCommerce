package com.skytala.eCommerce.domain.product.relations.productPriceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceType.model.ProductPriceType;
public class ProductPriceTypeDeleted implements Event{

	private boolean success;

	public ProductPriceTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
