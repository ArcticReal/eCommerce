package com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model.RejectionReason;
public class RejectionReasonAdded implements Event{

	private RejectionReason addedRejectionReason;
	private boolean success;

	public RejectionReasonAdded(RejectionReason addedRejectionReason, boolean success){
		this.addedRejectionReason = addedRejectionReason;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RejectionReason getAddedRejectionReason() {
		return addedRejectionReason;
	}

}
