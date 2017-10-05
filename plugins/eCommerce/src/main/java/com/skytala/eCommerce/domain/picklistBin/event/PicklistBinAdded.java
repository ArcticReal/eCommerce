package com.skytala.eCommerce.domain.picklistBin.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.picklistBin.model.PicklistBin;
public class PicklistBinAdded implements Event{

	private PicklistBin addedPicklistBin;
	private boolean success;

	public PicklistBinAdded(PicklistBin addedPicklistBin, boolean success){
		this.addedPicklistBin = addedPicklistBin;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PicklistBin getAddedPicklistBin() {
		return addedPicklistBin;
	}

}
