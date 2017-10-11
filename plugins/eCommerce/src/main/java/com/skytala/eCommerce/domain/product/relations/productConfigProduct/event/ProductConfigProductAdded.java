package com.skytala.eCommerce.domain.product.relations.productConfigProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigProduct.model.ProductConfigProduct;
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
