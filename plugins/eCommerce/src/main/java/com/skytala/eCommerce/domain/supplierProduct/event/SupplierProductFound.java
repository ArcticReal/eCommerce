package com.skytala.eCommerce.domain.supplierProduct.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.supplierProduct.model.SupplierProduct;
public class SupplierProductFound implements Event{

	private List<SupplierProduct> supplierProducts;

	public SupplierProductFound(List<SupplierProduct> supplierProducts) {
		this.supplierProducts = supplierProducts;
	}

	public List<SupplierProduct> getSupplierProducts()	{
		return supplierProducts;
	}

}
