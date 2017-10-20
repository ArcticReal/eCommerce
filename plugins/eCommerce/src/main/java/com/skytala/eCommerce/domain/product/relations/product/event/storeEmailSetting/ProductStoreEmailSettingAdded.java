package com.skytala.eCommerce.domain.product.relations.product.event.storeEmailSetting;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeEmailSetting.ProductStoreEmailSetting;
public class ProductStoreEmailSettingAdded implements Event{

	private ProductStoreEmailSetting addedProductStoreEmailSetting;
	private boolean success;

	public ProductStoreEmailSettingAdded(ProductStoreEmailSetting addedProductStoreEmailSetting, boolean success){
		this.addedProductStoreEmailSetting = addedProductStoreEmailSetting;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreEmailSetting getAddedProductStoreEmailSetting() {
		return addedProductStoreEmailSetting;
	}

}
