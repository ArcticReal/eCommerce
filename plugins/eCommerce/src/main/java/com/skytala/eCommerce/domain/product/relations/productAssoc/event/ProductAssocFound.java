package com.skytala.eCommerce.domain.product.relations.productAssoc.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productAssoc.model.ProductAssoc;
public class ProductAssocFound implements Event{

	private List<ProductAssoc> productAssocs;

	public ProductAssocFound(List<ProductAssoc> productAssocs) {
		this.productAssocs = productAssocs;
	}

	public List<ProductAssoc> getProductAssocs()	{
		return productAssocs;
	}

}
