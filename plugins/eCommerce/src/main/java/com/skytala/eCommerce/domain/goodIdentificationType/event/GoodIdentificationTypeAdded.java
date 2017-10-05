package com.skytala.eCommerce.domain.goodIdentificationType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.goodIdentificationType.model.GoodIdentificationType;
public class GoodIdentificationTypeAdded implements Event{

	private GoodIdentificationType addedGoodIdentificationType;
	private boolean success;

	public GoodIdentificationTypeAdded(GoodIdentificationType addedGoodIdentificationType, boolean success){
		this.addedGoodIdentificationType = addedGoodIdentificationType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public GoodIdentificationType getAddedGoodIdentificationType() {
		return addedGoodIdentificationType;
	}

}
