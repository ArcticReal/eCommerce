package com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.model.ProductFeatureCatGrpAppl;
public class ProductFeatureCatGrpApplFound implements Event{

	private List<ProductFeatureCatGrpAppl> productFeatureCatGrpAppls;

	public ProductFeatureCatGrpApplFound(List<ProductFeatureCatGrpAppl> productFeatureCatGrpAppls) {
		this.productFeatureCatGrpAppls = productFeatureCatGrpAppls;
	}

	public List<ProductFeatureCatGrpAppl> getProductFeatureCatGrpAppls()	{
		return productFeatureCatGrpAppls;
	}

}
