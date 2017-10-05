package com.skytala.eCommerce.domain.supplierRatingType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.supplierRatingType.model.SupplierRatingType;
public class SupplierRatingTypeAdded implements Event{

	private SupplierRatingType addedSupplierRatingType;
	private boolean success;

	public SupplierRatingTypeAdded(SupplierRatingType addedSupplierRatingType, boolean success){
		this.addedSupplierRatingType = addedSupplierRatingType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SupplierRatingType getAddedSupplierRatingType() {
		return addedSupplierRatingType;
	}

}
