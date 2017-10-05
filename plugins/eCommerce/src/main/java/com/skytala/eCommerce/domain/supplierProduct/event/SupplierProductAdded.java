package com.skytala.eCommerce.domain.supplierProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.supplierProduct.model.SupplierProduct;
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
