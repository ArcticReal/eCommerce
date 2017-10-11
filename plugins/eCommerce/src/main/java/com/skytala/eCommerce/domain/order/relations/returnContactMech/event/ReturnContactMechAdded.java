package com.skytala.eCommerce.domain.order.relations.returnContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnContactMech.model.ReturnContactMech;
public class ReturnContactMechAdded implements Event{

	private ReturnContactMech addedReturnContactMech;
	private boolean success;

	public ReturnContactMechAdded(ReturnContactMech addedReturnContactMech, boolean success){
		this.addedReturnContactMech = addedReturnContactMech;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnContactMech getAddedReturnContactMech() {
		return addedReturnContactMech;
	}

}
