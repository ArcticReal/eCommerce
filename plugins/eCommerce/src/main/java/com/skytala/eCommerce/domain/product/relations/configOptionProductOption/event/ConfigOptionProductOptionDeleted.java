package com.skytala.eCommerce.domain.product.relations.configOptionProductOption.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.model.ConfigOptionProductOption;
public class ConfigOptionProductOptionDeleted implements Event{

	private boolean success;

	public ConfigOptionProductOptionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
