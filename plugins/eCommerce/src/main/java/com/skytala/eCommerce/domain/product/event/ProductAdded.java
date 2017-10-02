package com.skytala.eCommerce.domain.product.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.model.Product;
public class ProductAdded implements Event{

	private Product addedProduct;
	private boolean success;

	public ProductAdded(Product addedProduct, boolean success){
		this.addedProduct = addedProduct;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Product getAddedProduct() {
		return addedProduct;
	}

}
