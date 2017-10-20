package com.skytala.eCommerce.domain.product.relations.product.event.storeFinActSetting;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeFinActSetting.ProductStoreFinActSetting;
public class ProductStoreFinActSettingDeleted implements Event{

	private boolean success;

	public ProductStoreFinActSettingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
