package com.skytala.eCommerce.domain.order.relations.requirement.event.orderCommitment;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.requirement.model.orderCommitment.OrderRequirementCommitment;
public class OrderRequirementCommitmentFound implements Event{

	private List<OrderRequirementCommitment> orderRequirementCommitments;

	public OrderRequirementCommitmentFound(List<OrderRequirementCommitment> orderRequirementCommitments) {
		this.orderRequirementCommitments = orderRequirementCommitments;
	}

	public List<OrderRequirementCommitment> getOrderRequirementCommitments()	{
		return orderRequirementCommitments;
	}

}
