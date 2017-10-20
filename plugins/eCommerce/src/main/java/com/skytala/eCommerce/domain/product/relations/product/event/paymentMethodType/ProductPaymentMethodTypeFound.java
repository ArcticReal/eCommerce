package com.skytala.eCommerce.domain.product.relations.product.event.paymentMethodType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.paymentMethodType.ProductPaymentMethodType;
public class ProductPaymentMethodTypeFound implements Event{

	private List<ProductPaymentMethodType> productPaymentMethodTypes;

	public ProductPaymentMethodTypeFound(List<ProductPaymentMethodType> productPaymentMethodTypes) {
		this.productPaymentMethodTypes = productPaymentMethodTypes;
	}

	public List<ProductPaymentMethodType> getProductPaymentMethodTypes()	{
		return productPaymentMethodTypes;
	}

}
