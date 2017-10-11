package com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureApplAttr.model.ProductFeatureApplAttr;
public class ProductFeatureApplAttrFound implements Event{

	private List<ProductFeatureApplAttr> productFeatureApplAttrs;

	public ProductFeatureApplAttrFound(List<ProductFeatureApplAttr> productFeatureApplAttrs) {
		this.productFeatureApplAttrs = productFeatureApplAttrs;
	}

	public List<ProductFeatureApplAttr> getProductFeatureApplAttrs()	{
		return productFeatureApplAttrs;
	}

}
