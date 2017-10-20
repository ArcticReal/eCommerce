package com.skytala.eCommerce.domain.product.relations.product.event.storePaymentSetting;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storePaymentSetting.ProductStorePaymentSetting;
public class ProductStorePaymentSettingDeleted implements Event{

	private boolean success;

	public ProductStorePaymentSettingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
