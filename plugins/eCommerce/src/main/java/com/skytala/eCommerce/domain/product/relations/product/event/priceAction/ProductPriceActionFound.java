package com.skytala.eCommerce.domain.product.relations.product.event.priceAction;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceAction.ProductPriceAction;
public class ProductPriceActionFound implements Event{

	private List<ProductPriceAction> productPriceActions;

	public ProductPriceActionFound(List<ProductPriceAction> productPriceActions) {
		this.productPriceActions = productPriceActions;
	}

	public List<ProductPriceAction> getProductPriceActions()	{
		return productPriceActions;
	}

}
