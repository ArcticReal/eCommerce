package com.skytala.eCommerce.domain.deliverable.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.deliverable.model.Deliverable;
public class DeliverableAdded implements Event{

	private Deliverable addedDeliverable;
	private boolean success;

	public DeliverableAdded(Deliverable addedDeliverable, boolean success){
		this.addedDeliverable = addedDeliverable;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Deliverable getAddedDeliverable() {
		return addedDeliverable;
	}

}
