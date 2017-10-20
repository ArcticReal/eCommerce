package com.skytala.eCommerce.domain.product.relations.product.event.featureGroupAppl;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureGroupAppl.ProductFeatureGroupAppl;
public class ProductFeatureGroupApplFound implements Event{

	private List<ProductFeatureGroupAppl> productFeatureGroupAppls;

	public ProductFeatureGroupApplFound(List<ProductFeatureGroupAppl> productFeatureGroupAppls) {
		this.productFeatureGroupAppls = productFeatureGroupAppls;
	}

	public List<ProductFeatureGroupAppl> getProductFeatureGroupAppls()	{
		return productFeatureGroupAppls;
	}

}
