package com.skytala.eCommerce.domain.product.relations.product.event.storeKeywordOvrd;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeKeywordOvrd.ProductStoreKeywordOvrd;
public class ProductStoreKeywordOvrdAdded implements Event{

	private ProductStoreKeywordOvrd addedProductStoreKeywordOvrd;
	private boolean success;

	public ProductStoreKeywordOvrdAdded(ProductStoreKeywordOvrd addedProductStoreKeywordOvrd, boolean success){
		this.addedProductStoreKeywordOvrd = addedProductStoreKeywordOvrd;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreKeywordOvrd getAddedProductStoreKeywordOvrd() {
		return addedProductStoreKeywordOvrd;
	}

}
