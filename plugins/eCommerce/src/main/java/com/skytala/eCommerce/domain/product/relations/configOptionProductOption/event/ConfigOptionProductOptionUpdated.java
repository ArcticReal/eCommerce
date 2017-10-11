package com.skytala.eCommerce.domain.product.relations.configOptionProductOption.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.model.ConfigOptionProductOption;
public class ConfigOptionProductOptionUpdated implements Event{

	private boolean success;

	public ConfigOptionProductOptionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
