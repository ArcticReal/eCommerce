package com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.model.ProductStoreFinActSetting;
public class ProductStoreFinActSettingFound implements Event{

	private List<ProductStoreFinActSetting> productStoreFinActSettings;

	public ProductStoreFinActSettingFound(List<ProductStoreFinActSetting> productStoreFinActSettings) {
		this.productStoreFinActSettings = productStoreFinActSettings;
	}

	public List<ProductStoreFinActSetting> getProductStoreFinActSettings()	{
		return productStoreFinActSettings;
	}

}
