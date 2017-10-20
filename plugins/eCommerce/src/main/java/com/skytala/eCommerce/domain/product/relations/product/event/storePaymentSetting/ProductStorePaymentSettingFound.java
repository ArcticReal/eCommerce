package com.skytala.eCommerce.domain.product.relations.product.event.storePaymentSetting;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storePaymentSetting.ProductStorePaymentSetting;
public class ProductStorePaymentSettingFound implements Event{

	private List<ProductStorePaymentSetting> productStorePaymentSettings;

	public ProductStorePaymentSettingFound(List<ProductStorePaymentSetting> productStorePaymentSettings) {
		this.productStorePaymentSettings = productStorePaymentSettings;
	}

	public List<ProductStorePaymentSetting> getProductStorePaymentSettings()	{
		return productStorePaymentSettings;
	}

}
