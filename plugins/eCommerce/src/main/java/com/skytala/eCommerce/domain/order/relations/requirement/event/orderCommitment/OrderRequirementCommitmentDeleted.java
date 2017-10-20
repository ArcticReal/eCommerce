package com.skytala.eCommerce.domain.order.relations.requirement.event.orderCommitment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.orderCommitment.OrderRequirementCommitment;
public class OrderRequirementCommitmentDeleted implements Event{

	private boolean success;

	public OrderRequirementCommitmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
