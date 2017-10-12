package com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model.RejectionReason;
public class RejectionReasonDeleted implements Event{

	private boolean success;

	public RejectionReasonDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
