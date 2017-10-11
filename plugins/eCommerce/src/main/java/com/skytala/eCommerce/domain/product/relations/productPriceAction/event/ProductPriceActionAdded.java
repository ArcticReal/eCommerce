package com.skytala.eCommerce.domain.product.relations.productPriceAction.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceAction.model.ProductPriceAction;
public class ProductPriceActionAdded implements Event{

	private ProductPriceAction addedProductPriceAction;
	private boolean success;

	public ProductPriceActionAdded(ProductPriceAction addedProductPriceAction, boolean success){
		this.addedProductPriceAction = addedProductPriceAction;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPriceAction getAddedProductPriceAction() {
		return addedProductPriceAction;
	}

}
