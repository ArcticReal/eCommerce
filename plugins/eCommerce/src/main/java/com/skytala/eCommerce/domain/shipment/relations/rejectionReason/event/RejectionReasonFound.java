package com.skytala.eCommerce.domain.shipment.relations.rejectionReason.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model.RejectionReason;
public class RejectionReasonFound implements Event{

	private List<RejectionReason> rejectionReasons;

	public RejectionReasonFound(List<RejectionReason> rejectionReasons) {
		this.rejectionReasons = rejectionReasons;
	}

	public List<RejectionReason> getRejectionReasons()	{
		return rejectionReasons;
	}

}
