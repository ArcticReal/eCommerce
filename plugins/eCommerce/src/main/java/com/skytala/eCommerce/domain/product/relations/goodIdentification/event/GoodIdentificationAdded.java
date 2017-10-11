package com.skytala.eCommerce.domain.product.relations.goodIdentification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.GoodIdentification;
public class GoodIdentificationAdded implements Event{

	private GoodIdentification addedGoodIdentification;
	private boolean success;

	public GoodIdentificationAdded(GoodIdentification addedGoodIdentification, boolean success){
		this.addedGoodIdentification = addedGoodIdentification;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GoodIdentification getAddedGoodIdentification() {
		return addedGoodIdentification;
	}

}
