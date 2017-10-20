package com.skytala.eCommerce.domain.product.relations.product.event.storeFinActSetting;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeFinActSetting.ProductStoreFinActSetting;
public class ProductStoreFinActSettingAdded implements Event{

	private ProductStoreFinActSetting addedProductStoreFinActSetting;
	private boolean success;

	public ProductStoreFinActSettingAdded(ProductStoreFinActSetting addedProductStoreFinActSetting, boolean success){
		this.addedProductStoreFinActSetting = addedProductStoreFinActSetting;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreFinActSetting getAddedProductStoreFinActSetting() {
		return addedProductStoreFinActSetting;
	}

}
