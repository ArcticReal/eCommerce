package com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.model.ProductFeatureCategoryAppl;
public class ProductFeatureCategoryApplFound implements Event{

	private List<ProductFeatureCategoryAppl> productFeatureCategoryAppls;

	public ProductFeatureCategoryApplFound(List<ProductFeatureCategoryAppl> productFeatureCategoryAppls) {
		this.productFeatureCategoryAppls = productFeatureCategoryAppls;
	}

	public List<ProductFeatureCategoryAppl> getProductFeatureCategoryAppls()	{
		return productFeatureCategoryAppls;
	}

}
