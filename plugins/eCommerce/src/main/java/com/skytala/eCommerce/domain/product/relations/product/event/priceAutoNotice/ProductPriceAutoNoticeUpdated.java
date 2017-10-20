package com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceAutoNotice.ProductPriceAutoNotice;
public class ProductPriceAutoNoticeUpdated implements Event{

	private boolean success;

	public ProductPriceAutoNoticeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
