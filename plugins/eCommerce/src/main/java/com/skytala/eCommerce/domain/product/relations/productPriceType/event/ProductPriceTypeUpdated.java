package com.skytala.eCommerce.domain.product.relations.productPriceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceType.model.ProductPriceType;
public class ProductPriceTypeUpdated implements Event{

	private boolean success;

	public ProductPriceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
