package com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.model.ProductStorePaymentSetting;
public class ProductStorePaymentSettingDeleted implements Event{

	private boolean success;

	public ProductStorePaymentSettingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
