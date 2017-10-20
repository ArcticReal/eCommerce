package com.skytala.eCommerce.domain.product.relations.product.event.storeVendorPayment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorPayment.ProductStoreVendorPayment;
public class ProductStoreVendorPaymentAdded implements Event{

	private ProductStoreVendorPayment addedProductStoreVendorPayment;
	private boolean success;

	public ProductStoreVendorPaymentAdded(ProductStoreVendorPayment addedProductStoreVendorPayment, boolean success){
		this.addedProductStoreVendorPayment = addedProductStoreVendorPayment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreVendorPayment getAddedProductStoreVendorPayment() {
		return addedProductStoreVendorPayment;
	}

}
