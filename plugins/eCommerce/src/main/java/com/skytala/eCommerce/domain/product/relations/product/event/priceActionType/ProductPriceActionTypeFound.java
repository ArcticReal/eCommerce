package com.skytala.eCommerce.domain.product.relations.product.event.priceActionType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceActionType.ProductPriceActionType;
public class ProductPriceActionTypeFound implements Event{

	private List<ProductPriceActionType> productPriceActionTypes;

	public ProductPriceActionTypeFound(List<ProductPriceActionType> productPriceActionTypes) {
		this.productPriceActionTypes = productPriceActionTypes;
	}

	public List<ProductPriceActionType> getProductPriceActionTypes()	{
		return productPriceActionTypes;
	}

}
