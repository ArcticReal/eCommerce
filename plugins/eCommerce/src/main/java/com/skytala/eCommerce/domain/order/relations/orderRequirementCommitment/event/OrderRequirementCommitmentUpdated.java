package com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.model.OrderRequirementCommitment;
public class OrderRequirementCommitmentUpdated implements Event{

	private boolean success;

	public OrderRequirementCommitmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
