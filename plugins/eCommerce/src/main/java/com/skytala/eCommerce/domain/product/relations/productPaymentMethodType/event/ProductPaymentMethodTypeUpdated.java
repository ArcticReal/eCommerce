package com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.model.ProductPaymentMethodType;
public class ProductPaymentMethodTypeUpdated implements Event{

	private boolean success;

	public ProductPaymentMethodTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
