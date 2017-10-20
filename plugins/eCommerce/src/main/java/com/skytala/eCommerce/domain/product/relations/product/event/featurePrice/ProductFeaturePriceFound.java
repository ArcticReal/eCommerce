package com.skytala.eCommerce.domain.product.relations.product.event.featurePrice;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featurePrice.ProductFeaturePrice;
public class ProductFeaturePriceFound implements Event{

	private List<ProductFeaturePrice> productFeaturePrices;

	public ProductFeaturePriceFound(List<ProductFeaturePrice> productFeaturePrices) {
		this.productFeaturePrices = productFeaturePrices;
	}

	public List<ProductFeaturePrice> getProductFeaturePrices()	{
		return productFeaturePrices;
	}

}
