package com.skytala.eCommerce.domain.product.relations.product.event.storeVendorPayment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeVendorPayment.ProductStoreVendorPayment;
public class ProductStoreVendorPaymentFound implements Event{

	private List<ProductStoreVendorPayment> productStoreVendorPayments;

	public ProductStoreVendorPaymentFound(List<ProductStoreVendorPayment> productStoreVendorPayments) {
		this.productStoreVendorPayments = productStoreVendorPayments;
	}

	public List<ProductStoreVendorPayment> getProductStoreVendorPayments()	{
		return productStoreVendorPayments;
	}

}
