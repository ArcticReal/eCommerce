package com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.model.ProductStoreEmailSetting;
public class ProductStoreEmailSettingFound implements Event{

	private List<ProductStoreEmailSetting> productStoreEmailSettings;

	public ProductStoreEmailSettingFound(List<ProductStoreEmailSetting> productStoreEmailSettings) {
		this.productStoreEmailSettings = productStoreEmailSettings;
	}

	public List<ProductStoreEmailSetting> getProductStoreEmailSettings()	{
		return productStoreEmailSettings;
	}

}
