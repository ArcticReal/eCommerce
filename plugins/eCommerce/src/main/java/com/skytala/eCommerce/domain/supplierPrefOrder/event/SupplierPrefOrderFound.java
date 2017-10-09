package com.skytala.eCommerce.domain.supplierPrefOrder.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.supplierPrefOrder.model.SupplierPrefOrder;
public class SupplierPrefOrderFound implements Event{

	private List<SupplierPrefOrder> supplierPrefOrders;

	public SupplierPrefOrderFound(List<SupplierPrefOrder> supplierPrefOrders) {
		this.supplierPrefOrders = supplierPrefOrders;
	}

	public List<SupplierPrefOrder> getSupplierPrefOrders()	{
		return supplierPrefOrders;
	}

}