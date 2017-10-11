package com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.model.ProductPriceAutoNotice;
public class ProductPriceAutoNoticeUpdated implements Event{

	private boolean success;

	public ProductPriceAutoNoticeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
