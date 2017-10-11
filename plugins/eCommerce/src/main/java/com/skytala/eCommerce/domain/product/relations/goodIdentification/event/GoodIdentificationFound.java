package com.skytala.eCommerce.domain.product.relations.goodIdentification.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.GoodIdentification;
public class GoodIdentificationFound implements Event{

	private List<GoodIdentification> goodIdentifications;

	public GoodIdentificationFound(List<GoodIdentification> goodIdentifications) {
		this.goodIdentifications = goodIdentifications;
	}

	public List<GoodIdentification> getGoodIdentifications()	{
		return goodIdentifications;
	}

}
