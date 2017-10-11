package com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.model.ProductStorePaymentSetting;
public class ProductStorePaymentSettingAdded implements Event{

	private ProductStorePaymentSetting addedProductStorePaymentSetting;
	private boolean success;

	public ProductStorePaymentSettingAdded(ProductStorePaymentSetting addedProductStorePaymentSetting, boolean success){
		this.addedProductStorePaymentSetting = addedProductStorePaymentSetting;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStorePaymentSetting getAddedProductStorePaymentSetting() {
		return addedProductStorePaymentSetting;
	}

}
