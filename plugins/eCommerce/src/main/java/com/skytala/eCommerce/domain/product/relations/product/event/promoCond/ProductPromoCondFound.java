package com.skytala.eCommerce.domain.product.relations.product.event.promoCond;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoCond.ProductPromoCond;
public class ProductPromoCondFound implements Event{

	private List<ProductPromoCond> productPromoConds;

	public ProductPromoCondFound(List<ProductPromoCond> productPromoConds) {
		this.productPromoConds = productPromoConds;
	}

	public List<ProductPromoCond> getProductPromoConds()	{
		return productPromoConds;
	}

}
