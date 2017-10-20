package com.skytala.eCommerce.domain.product.relations.goodIdentification.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.type.GoodIdentificationType;
public class GoodIdentificationTypeFound implements Event{

	private List<GoodIdentificationType> goodIdentificationTypes;

	public GoodIdentificationTypeFound(List<GoodIdentificationType> goodIdentificationTypes) {
		this.goodIdentificationTypes = goodIdentificationTypes;
	}

	public List<GoodIdentificationType> getGoodIdentificationTypes()	{
		return goodIdentificationTypes;
	}

}
