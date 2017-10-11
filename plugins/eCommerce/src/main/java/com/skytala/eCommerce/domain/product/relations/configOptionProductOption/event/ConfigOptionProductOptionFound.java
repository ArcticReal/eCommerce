package com.skytala.eCommerce.domain.product.relations.configOptionProductOption.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.configOptionProductOption.model.ConfigOptionProductOption;
public class ConfigOptionProductOptionFound implements Event{

	private List<ConfigOptionProductOption> configOptionProductOptions;

	public ConfigOptionProductOptionFound(List<ConfigOptionProductOption> configOptionProductOptions) {
		this.configOptionProductOptions = configOptionProductOptions;
	}

	public List<ConfigOptionProductOption> getConfigOptionProductOptions()	{
		return configOptionProductOptions;
	}

}
