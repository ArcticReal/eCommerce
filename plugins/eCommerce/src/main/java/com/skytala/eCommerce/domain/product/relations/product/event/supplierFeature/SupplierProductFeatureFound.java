package com.skytala.eCommerce.domain.product.relations.product.event.supplierFeature;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.supplierFeature.SupplierProductFeature;
public class SupplierProductFeatureFound implements Event{

	private List<SupplierProductFeature> supplierProductFeatures;

	public SupplierProductFeatureFound(List<SupplierProductFeature> supplierProductFeatures) {
		this.supplierProductFeatures = supplierProductFeatures;
	}

	public List<SupplierProductFeature> getSupplierProductFeatures()	{
		return supplierProductFeatures;
	}

}
