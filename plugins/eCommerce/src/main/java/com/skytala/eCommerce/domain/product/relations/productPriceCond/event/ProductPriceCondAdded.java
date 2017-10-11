package com.skytala.eCommerce.domain.product.relations.productPriceCond.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceCond.model.ProductPriceCond;
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
