package com.skytala.eCommerce.domain.product.relations.product.event.supplier;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.supplier.SupplierProduct;
public class SupplierProductAdded implements Event{

	private SupplierProduct addedSupplierProduct;
	private boolean success;

	public SupplierProductAdded(SupplierProduct addedSupplierProduct, boolean success){
		this.addedSupplierProduct = addedSupplierProduct;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SupplierProduct getAddedSupplierProduct() {
		return addedSupplierProduct;
	}

}
