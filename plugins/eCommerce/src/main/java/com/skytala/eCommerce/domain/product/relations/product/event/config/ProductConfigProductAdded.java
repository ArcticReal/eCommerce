package com.skytala.eCommerce.domain.product.relations.product.event.config;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.config.ProductConfigProduct;
public class ProductConfigProductAdded implements Event{

	private ProductConfigProduct addedProductConfigProduct;
	private boolean success;

	public ProductConfigProductAdded(ProductConfigProduct addedProductConfigProduct, boolean success){
		this.addedProductConfigProduct = addedProductConfigProduct;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductConfigProduct getAddedProductConfigProduct() {
		return addedProductConfigProduct;
	}

}
