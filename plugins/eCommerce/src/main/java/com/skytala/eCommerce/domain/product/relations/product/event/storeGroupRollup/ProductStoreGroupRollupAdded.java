package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRollup.ProductStoreGroupRollup;
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
