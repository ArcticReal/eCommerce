package com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupRollup.model.ProductStoreGroupRollup;
public class ProductStoreGroupRollupAdded implements Event{

	private ProductStoreGroupRollup addedProductStoreGroupRollup;
	private boolean success;

	public ProductStoreGroupRollupAdded(ProductStoreGroupRollup addedProductStoreGroupRollup, boolean success){
		this.addedProductStoreGroupRollup = addedProductStoreGroupRollup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreGroupRollup getAddedProductStoreGroupRollup() {
		return addedProductStoreGroupRollup;
	}

}
