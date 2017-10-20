package com.skytala.eCommerce.domain.product.relations.product.event.storeFinActSetting;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeFinActSetting.ProductStoreFinActSetting;
public class ProductStoreFinActSettingUpdated implements Event{

	private boolean success;

	public ProductStoreFinActSettingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
