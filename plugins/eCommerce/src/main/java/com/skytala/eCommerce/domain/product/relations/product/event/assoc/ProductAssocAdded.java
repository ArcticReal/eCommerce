package com.skytala.eCommerce.domain.product.relations.product.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.assoc.ProductAssoc;
public class ProductAssocAdded implements Event{

	private ProductAssoc addedProductAssoc;
	private boolean success;

	public ProductAssocAdded(ProductAssoc addedProductAssoc, boolean success){
		this.addedProductAssoc = addedProductAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductAssoc getAddedProductAssoc() {
		return addedProductAssoc;
	}

}
