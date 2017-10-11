package com.skytala.eCommerce.domain.product.relations.productPromoCond.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCond.model.ProductPromoCond;
public class ProductPromoCondAdded implements Event{

	private ProductPromoCond addedProductPromoCond;
	private boolean success;

	public ProductPromoCondAdded(ProductPromoCond addedProductPromoCond, boolean success){
		this.addedProductPromoCond = addedProductPromoCond;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoCond getAddedProductPromoCond() {
		return addedProductPromoCond;
	}

}
