package com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.paymentMethodType.ProductPaymentMethodType;
public class ProductPaymentMethodTypeDeleted implements Event{

	private boolean success;

	public ProductPaymentMethodTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}