package com.skytala.eCommerce.domain.product.relations.productFeatureAppl.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.model.ProductFeatureAppl;
public class ProductFeatureApplFound implements Event{

	private List<ProductFeatureAppl> productFeatureAppls;

	public ProductFeatureApplFound(List<ProductFeatureAppl> productFeatureAppls) {
		this.productFeatureAppls = productFeatureAppls;
	}

	public List<ProductFeatureAppl> getProductFeatureAppls()	{
		return productFeatureAppls;
	}

}
