package com.skytala.eCommerce.domain.product.relations.vendorProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.vendorProduct.model.VendorProduct;
public class VendorProductAdded implements Event{

	private VendorProduct addedVendorProduct;
	private boolean success;

	public VendorProductAdded(VendorProduct addedVendorProduct, boolean success){
		this.addedVendorProduct = addedVendorProduct;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public VendorProduct getAddedVendorProduct() {
		return addedVendorProduct;
	}

}
