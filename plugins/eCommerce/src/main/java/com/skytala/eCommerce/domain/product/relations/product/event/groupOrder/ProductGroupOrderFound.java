package com.skytala.eCommerce.domain.product.relations.product.event.groupOrder;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.groupOrder.ProductGroupOrder;
public class ProductGroupOrderFound implements Event{

	private List<ProductGroupOrder> productGroupOrders;

	public ProductGroupOrderFound(List<ProductGroupOrder> productGroupOrders) {
		this.productGroupOrders = productGroupOrders;
	}

	public List<ProductGroupOrder> getProductGroupOrders()	{
		return productGroupOrders;
	}

}
