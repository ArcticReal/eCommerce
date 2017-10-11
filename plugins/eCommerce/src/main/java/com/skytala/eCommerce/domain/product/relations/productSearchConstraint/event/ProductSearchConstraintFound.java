package com.skytala.eCommerce.domain.product.relations.productSearchConstraint.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.model.ProductSearchConstraint;
public class ProductSearchConstraintFound implements Event{

	private List<ProductSearchConstraint> productSearchConstraints;

	public ProductSearchConstraintFound(List<ProductSearchConstraint> productSearchConstraints) {
		this.productSearchConstraints = productSearchConstraints;
	}

	public List<ProductSearchConstraint> getProductSearchConstraints()	{
		return productSearchConstraints;
	}

}
