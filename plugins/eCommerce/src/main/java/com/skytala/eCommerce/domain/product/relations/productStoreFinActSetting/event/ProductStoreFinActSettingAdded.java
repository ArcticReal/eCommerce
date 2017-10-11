package com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.model.ProductStoreFinActSetting;
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
