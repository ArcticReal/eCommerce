package com.skytala.eCommerce.domain.order.relations.requirement.event.orderCommitment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.orderCommitment.OrderRequirementCommitment;
public class OrderRequirementCommitmentAdded implements Event{

	private OrderRequirementCommitment addedOrderRequirementCommitment;
	private boolean success;

	public OrderRequirementCommitmentAdded(OrderRequirementCommitment addedOrderRequirementCommitment, boolean success){
		this.addedOrderRequirementCommitment = addedOrderRequirementCommitment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderRequirementCommitment getAddedOrderRequirementCommitment() {
		return addedOrderRequirementCommitment;
	}

}
