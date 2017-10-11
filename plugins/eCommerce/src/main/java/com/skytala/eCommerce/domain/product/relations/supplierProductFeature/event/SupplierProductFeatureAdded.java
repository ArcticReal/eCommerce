package com.skytala.eCommerce.domain.product.relations.supplierProductFeature.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.model.SupplierProductFeature;
public class SupplierProductFeatureAdded implements Event{

	private SupplierProductFeature addedSupplierProductFeature;
	private boolean success;

	public SupplierProductFeatureAdded(SupplierProductFeature addedSupplierProductFeature, boolean success){
		this.addedSupplierProductFeature = addedSupplierProductFeature;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SupplierProductFeature getAddedSupplierProductFeature() {
		return addedSupplierProductFeature;
	}

}
