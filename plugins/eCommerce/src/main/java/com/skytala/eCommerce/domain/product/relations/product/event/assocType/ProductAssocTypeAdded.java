package com.skytala.eCommerce.domain.product.relations.product.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.assocType.ProductAssocType;
public class ProductAssocTypeAdded implements Event{

	private ProductAssocType addedProductAssocType;
	private boolean success;

	public ProductAssocTypeAdded(ProductAssocType addedProductAssocType, boolean success){
		this.addedProductAssocType = addedProductAssocType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductAssocType getAddedProductAssocType() {
		return addedProductAssocType;
	}

}
