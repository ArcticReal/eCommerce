package com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model.RejectionReason;
public class RejectionReasonUpdated implements Event{

	private boolean success;

	public RejectionReasonUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
