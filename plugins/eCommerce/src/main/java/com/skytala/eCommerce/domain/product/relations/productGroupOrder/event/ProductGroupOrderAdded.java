package com.skytala.eCommerce.domain.product.relations.productGroupOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productGroupOrder.model.ProductGroupOrder;
public class ProductGroupOrderAdded implements Event{

	private ProductGroupOrder addedProductGroupOrder;
	private boolean success;

	public ProductGroupOrderAdded(ProductGroupOrder addedProductGroupOrder, boolean success){
		this.addedProductGroupOrder = addedProductGroupOrder;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductGroupOrder getAddedProductGroupOrder() {
		return addedProductGroupOrder;
	}

}
