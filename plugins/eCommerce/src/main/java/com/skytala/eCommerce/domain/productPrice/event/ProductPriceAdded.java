package com.skytala.eCommerce.domain.productPrice.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productPrice.model.ProductPrice;
public class ProductPriceAdded implements Event{

	private ProductPrice addedProductPrice;
	private boolean success;

	public ProductPriceAdded(ProductPrice addedProductPrice, boolean success){
		this.addedProductPrice = addedProductPrice;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPrice getAddedProductPrice() {
		return addedProductPrice;
	}

}
