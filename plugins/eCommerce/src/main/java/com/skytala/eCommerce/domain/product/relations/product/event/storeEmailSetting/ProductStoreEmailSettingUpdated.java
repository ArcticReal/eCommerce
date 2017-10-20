package com.skytala.eCommerce.domain.product.relations.product.event.storeEmailSetting;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeEmailSetting.ProductStoreEmailSetting;
public class ProductStoreEmailSettingUpdated implements Event{

	private boolean success;

	public ProductStoreEmailSettingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
