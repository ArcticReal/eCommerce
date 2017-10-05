package com.skytala.eCommerce.domain.productStoreGroupType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productStoreGroupType.model.ProductStoreGroupType;
public class ProductStoreGroupTypeFound implements Event{

	private List<ProductStoreGroupType> productStoreGroupTypes;

	public ProductStoreGroupTypeFound(List<ProductStoreGroupType> productStoreGroupTypes) {
		this.productStoreGroupTypes = productStoreGroupTypes;
	}

	public List<ProductStoreGroupType> getProductStoreGroupTypes()	{
		return productStoreGroupTypes;
	}

}
