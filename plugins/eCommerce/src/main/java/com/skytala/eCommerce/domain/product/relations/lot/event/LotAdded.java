package com.skytala.eCommerce.domain.product.relations.lot.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.lot.model.Lot;
public class LotAdded implements Event{

	private Lot addedLot;
	private boolean success;

	public LotAdded(Lot addedLot, boolean success){
		this.addedLot = addedLot;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Lot getAddedLot() {
		return addedLot;
	}

}
