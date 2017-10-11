package com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.model.ProductStoreEmailSetting;
public class ProductStoreEmailSettingUpdated implements Event{

	private boolean success;

	public ProductStoreEmailSettingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
