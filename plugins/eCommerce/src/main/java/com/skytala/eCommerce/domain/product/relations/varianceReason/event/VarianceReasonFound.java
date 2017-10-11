package com.skytala.eCommerce.domain.product.relations.varianceReason.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.varianceReason.model.VarianceReason;
public class VarianceReasonFound implements Event{

	private List<VarianceReason> varianceReasons;

	public VarianceReasonFound(List<VarianceReason> varianceReasons) {
		this.varianceReasons = varianceReasons;
	}

	public List<VarianceReason> getVarianceReasons()	{
		return varianceReasons;
	}

}
