package com.skytala.eCommerce.domain.product.relations.configOptionProductOption.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.model.ConfigOptionProductOption;
public class ConfigOptionProductOptionAdded implements Event{

	private ConfigOptionProductOption addedConfigOptionProductOption;
	private boolean success;

	public ConfigOptionProductOptionAdded(ConfigOptionProductOption addedConfigOptionProductOption, boolean success){
		this.addedConfigOptionProductOption = addedConfigOptionProductOption;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ConfigOptionProductOption getAddedConfigOptionProductOption() {
		return addedConfigOptionProductOption;
	}

}
