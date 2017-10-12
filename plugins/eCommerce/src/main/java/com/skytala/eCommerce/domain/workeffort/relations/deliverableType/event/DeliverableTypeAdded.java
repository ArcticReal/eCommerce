package com.skytala.eCommerce.domain.workeffort.relations.deliverableType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverableType.model.DeliverableType;
public class DeliverableTypeAdded implements Event{

	private DeliverableType addedDeliverableType;
	private boolean success;

	public DeliverableTypeAdded(DeliverableType addedDeliverableType, boolean success){
		this.addedDeliverableType = addedDeliverableType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DeliverableType getAddedDeliverableType() {
		return addedDeliverableType;
	}

}
