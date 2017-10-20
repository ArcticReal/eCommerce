package com.skytala.eCommerce.domain.product.relations.product.event.vendor;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.vendor.VendorProduct;
public class VendorProductFound implements Event{

	private List<VendorProduct> vendorProducts;

	public VendorProductFound(List<VendorProduct> vendorProducts) {
		this.vendorProducts = vendorProducts;
	}

	public List<VendorProduct> getVendorProducts()	{
		return vendorProducts;
	}

}
