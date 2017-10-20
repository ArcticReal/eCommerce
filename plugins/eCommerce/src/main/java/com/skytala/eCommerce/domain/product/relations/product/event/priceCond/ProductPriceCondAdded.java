package com.skytala.eCommerce.domain.product.relations.product.event.priceCond;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceCond.ProductPriceCond;
public class ProductPriceCondAdded implements Event{

	private ProductPriceCond addedProductPriceCond;
	private boolean success;

	public ProductPriceCondAdded(ProductPriceCond addedProductPriceCond, boolean success){
		this.addedProductPriceCond = addedProductPriceCond;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPriceCond getAddedProductPriceCond() {
		return addedProductPriceCond;
	}

}
