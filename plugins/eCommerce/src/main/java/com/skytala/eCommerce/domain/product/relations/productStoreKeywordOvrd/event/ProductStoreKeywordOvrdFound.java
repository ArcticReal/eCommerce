package com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreKeywordOvrd.model.ProductStoreKeywordOvrd;
public class ProductStoreKeywordOvrdFound implements Event{

	private List<ProductStoreKeywordOvrd> productStoreKeywordOvrds;

	public ProductStoreKeywordOvrdFound(List<ProductStoreKeywordOvrd> productStoreKeywordOvrds) {
		this.productStoreKeywordOvrds = productStoreKeywordOvrds;
	}

	public List<ProductStoreKeywordOvrd> getProductStoreKeywordOvrds()	{
		return productStoreKeywordOvrds;
	}

}
