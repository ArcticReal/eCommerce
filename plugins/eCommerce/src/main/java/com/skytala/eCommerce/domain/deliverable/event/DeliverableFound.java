package com.skytala.eCommerce.domain.deliverable.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.deliverable.model.Deliverable;
public class DeliverableFound implements Event{

	private List<Deliverable> deliverables;

	public DeliverableFound(List<Deliverable> deliverables) {
		this.deliverables = deliverables;
	}

	public List<Deliverable> getDeliverables()	{
		return deliverables;
	}

}
