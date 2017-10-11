package com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productConfigOptionIactn.model.ProductConfigOptionIactn;
public class ProductConfigOptionIactnFound implements Event{

	private List<ProductConfigOptionIactn> productConfigOptionIactns;

	public ProductConfigOptionIactnFound(List<ProductConfigOptionIactn> productConfigOptionIactns) {
		this.productConfigOptionIactns = productConfigOptionIactns;
	}

	public List<ProductConfigOptionIactn> getProductConfigOptionIactns()	{
		return productConfigOptionIactns;
	}

}
