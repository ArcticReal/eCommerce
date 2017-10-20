package com.skytala.eCommerce.domain.product.relations.product.event.featureAppl;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureAppl.ProductFeatureAppl;
public class ProductFeatureApplFound implements Event{

	private List<ProductFeatureAppl> productFeatureAppls;

	public ProductFeatureApplFound(List<ProductFeatureAppl> productFeatureAppls) {
		this.productFeatureAppls = productFeatureAppls;
	}

	public List<ProductFeatureAppl> getProductFeatureAppls()	{
		return productFeatureAppls;
	}

}
