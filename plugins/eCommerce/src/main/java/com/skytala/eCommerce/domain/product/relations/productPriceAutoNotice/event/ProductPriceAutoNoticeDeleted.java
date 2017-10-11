package com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceAutoNotice.model.ProductPriceAutoNotice;
public class ProductPriceAutoNoticeDeleted implements Event{

	private boolean success;

	public ProductPriceAutoNoticeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
