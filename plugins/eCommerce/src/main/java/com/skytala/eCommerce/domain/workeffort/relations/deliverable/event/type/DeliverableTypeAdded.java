package com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.type.DeliverableType;
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
