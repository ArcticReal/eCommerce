package com.skytala.eCommerce.domain.productAssocType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productAssocType.model.ProductAssocType;
public class ProductAssocTypeFound implements Event{

	private List<ProductAssocType> productAssocTypes;

	public ProductAssocTypeFound(List<ProductAssocType> productAssocTypes) {
		this.productAssocTypes = productAssocTypes;
	}

	public List<ProductAssocType> getProductAssocTypes()	{
		return productAssocTypes;
	}

}
