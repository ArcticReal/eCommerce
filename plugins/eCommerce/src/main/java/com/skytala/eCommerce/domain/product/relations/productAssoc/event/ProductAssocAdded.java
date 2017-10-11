package com.skytala.eCommerce.domain.product.relations.productAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productAssoc.model.ProductAssoc;
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
