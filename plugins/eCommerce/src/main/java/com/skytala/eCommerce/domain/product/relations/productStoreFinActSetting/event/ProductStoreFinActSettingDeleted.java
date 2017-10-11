package com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.model.ProductStoreFinActSetting;
public class ProductStoreFinActSettingDeleted implements Event{

	private boolean success;

	public ProductStoreFinActSettingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
