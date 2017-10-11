package com.skytala.eCommerce.domain.product.relations.productFeatureIactn.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureIactn.model.ProductFeatureIactn;
public class ProductFeatureIactnFound implements Event{

	private List<ProductFeatureIactn> productFeatureIactns;

	public ProductFeatureIactnFound(List<ProductFeatureIactn> productFeatureIactns) {
		this.productFeatureIactns = productFeatureIactns;
	}

	public List<ProductFeatureIactn> getProductFeatureIactns()	{
		return productFeatureIactns;
	}

}
