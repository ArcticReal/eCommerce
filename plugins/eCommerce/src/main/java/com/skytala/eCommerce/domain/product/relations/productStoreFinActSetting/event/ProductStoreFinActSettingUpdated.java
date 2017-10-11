package com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.model.ProductStoreFinActSetting;
public class ProductStoreFinActSettingUpdated implements Event{

	private boolean success;

	public ProductStoreFinActSettingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
