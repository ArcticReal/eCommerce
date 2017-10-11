package com.skytala.eCommerce.domain.product.relations.goodIdentificationType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.goodIdentificationType.model.GoodIdentificationType;
public class GoodIdentificationTypeFound implements Event{

	private List<GoodIdentificationType> goodIdentificationTypes;

	public GoodIdentificationTypeFound(List<GoodIdentificationType> goodIdentificationTypes) {
		this.goodIdentificationTypes = goodIdentificationTypes;
	}

	public List<GoodIdentificationType> getGoodIdentificationTypes()	{
		return goodIdentificationTypes;
	}

}
