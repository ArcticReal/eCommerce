package com.skytala.eCommerce.domain.supplierRatingType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.supplierRatingType.model.SupplierRatingType;
public class SupplierRatingTypeFound implements Event{

	private List<SupplierRatingType> supplierRatingTypes;

	public SupplierRatingTypeFound(List<SupplierRatingType> supplierRatingTypes) {
		this.supplierRatingTypes = supplierRatingTypes;
	}

	public List<SupplierRatingType> getSupplierRatingTypes()	{
		return supplierRatingTypes;
	}

}
