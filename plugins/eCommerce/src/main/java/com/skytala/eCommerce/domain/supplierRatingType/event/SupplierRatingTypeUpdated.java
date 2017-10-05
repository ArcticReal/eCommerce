package com.skytala.eCommerce.domain.supplierRatingType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.supplierRatingType.model.SupplierRatingType;
public class SupplierRatingTypeUpdated implements Event{

	private boolean success;

	public SupplierRatingTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
