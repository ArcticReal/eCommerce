package com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.model.OrderRequirementCommitment;
public class OrderRequirementCommitmentDeleted implements Event{

	private boolean success;

	public OrderRequirementCommitmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
