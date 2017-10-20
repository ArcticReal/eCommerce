package com.skytala.eCommerce.domain.product.relations.product.event.storePromoAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storePromoAppl.ProductStorePromoAppl;
public class ProductStorePromoApplAdded implements Event{

	private ProductStorePromoAppl addedProductStorePromoAppl;
	private boolean success;

	public ProductStorePromoApplAdded(ProductStorePromoAppl addedProductStorePromoAppl, boolean success){
		this.addedProductStorePromoAppl = addedProductStorePromoAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStorePromoAppl getAddedProductStorePromoAppl() {
		return addedProductStorePromoAppl;
	}

}
