package com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.paymentMethodType.ProductPaymentMethodType;
public class ProductPaymentMethodTypeAdded implements Event{

	private ProductPaymentMethodType addedProductPaymentMethodType;
	private boolean success;

	public ProductPaymentMethodTypeAdded(ProductPaymentMethodType addedProductPaymentMethodType, boolean success){
		this.addedProductPaymentMethodType = addedProductPaymentMethodType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPaymentMethodType getAddedProductPaymentMethodType() {
		return addedProductPaymentMethodType;
	}

}
