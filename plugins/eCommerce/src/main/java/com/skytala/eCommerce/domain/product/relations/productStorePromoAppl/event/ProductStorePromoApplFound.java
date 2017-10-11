package com.skytala.eCommerce.domain.product.relations.productStorePromoAppl.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStorePromoAppl.model.ProductStorePromoAppl;
public class ProductStorePromoApplFound implements Event{

	private List<ProductStorePromoAppl> productStorePromoAppls;

	public ProductStorePromoApplFound(List<ProductStorePromoAppl> productStorePromoAppls) {
		this.productStorePromoAppls = productStorePromoAppls;
	}

	public List<ProductStorePromoAppl> getProductStorePromoAppls()	{
		return productStorePromoAppls;
	}

}
