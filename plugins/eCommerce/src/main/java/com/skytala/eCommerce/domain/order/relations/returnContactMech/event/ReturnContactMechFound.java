package com.skytala.eCommerce.domain.order.relations.returnContactMech.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnContactMech.model.ReturnContactMech;
public class ReturnContactMechFound implements Event{

	private List<ReturnContactMech> returnContactMechs;

	public ReturnContactMechFound(List<ReturnContactMech> returnContactMechs) {
		this.returnContactMechs = returnContactMechs;
	}

	public List<ReturnContactMech> getReturnContactMechs()	{
		return returnContactMechs;
	}

}
