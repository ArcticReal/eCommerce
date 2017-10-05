package com.skytala.eCommerce.domain.supplierPrefOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.supplierPrefOrder.model.SupplierPrefOrder;
public class SupplierPrefOrderAdded implements Event{

	private SupplierPrefOrder addedSupplierPrefOrder;
	private boolean success;

	public SupplierPrefOrderAdded(SupplierPrefOrder addedSupplierPrefOrder, boolean success){
		this.addedSupplierPrefOrder = addedSupplierPrefOrder;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SupplierPrefOrder getAddedSupplierPrefOrder() {
		return addedSupplierPrefOrder;
	}

}
