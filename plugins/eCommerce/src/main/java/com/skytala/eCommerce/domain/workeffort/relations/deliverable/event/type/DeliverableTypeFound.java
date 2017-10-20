package com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.type.DeliverableType;
public class DeliverableTypeFound implements Event{

	private List<DeliverableType> deliverableTypes;

	public DeliverableTypeFound(List<DeliverableType> deliverableTypes) {
		this.deliverableTypes = deliverableTypes;
	}

	public List<DeliverableType> getDeliverableTypes()	{
		return deliverableTypes;
	}

}
