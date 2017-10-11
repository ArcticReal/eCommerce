package com.skytala.eCommerce.domain.product.relations.productFeaturePrice.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeaturePrice.model.ProductFeaturePrice;
public class ProductFeaturePriceFound implements Event{

	private List<ProductFeaturePrice> productFeaturePrices;

	public ProductFeaturePriceFound(List<ProductFeaturePrice> productFeaturePrices) {
		this.productFeaturePrices = productFeaturePrices;
	}

	public List<ProductFeaturePrice> getProductFeaturePrices()	{
		return productFeaturePrices;
	}

}
