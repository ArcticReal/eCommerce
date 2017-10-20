package com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeKeywordOvrd.ProductStoreKeywordOvrd;
public class ProductStoreKeywordOvrdFound implements Event{

	private List<ProductStoreKeywordOvrd> productStoreKeywordOvrds;

	public ProductStoreKeywordOvrdFound(List<ProductStoreKeywordOvrd> productStoreKeywordOvrds) {
		this.productStoreKeywordOvrds = productStoreKeywordOvrds;
	}

	public List<ProductStoreKeywordOvrd> getProductStoreKeywordOvrds()	{
		return productStoreKeywordOvrds;
	}

}
