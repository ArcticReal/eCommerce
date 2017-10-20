package com.skytala.eCommerce.domain.product.relations.product.event.groupOrder;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.groupOrder.ProductGroupOrder;
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
