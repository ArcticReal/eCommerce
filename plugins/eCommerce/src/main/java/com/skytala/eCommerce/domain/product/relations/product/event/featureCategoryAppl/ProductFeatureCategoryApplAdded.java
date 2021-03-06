package com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureCategoryAppl.ProductFeatureCategoryAppl;
public class ProductFeatureCategoryApplAdded implements Event{

	private ProductFeatureCategoryAppl addedProductFeatureCategoryAppl;
	private boolean success;

	public ProductFeatureCategoryApplAdded(ProductFeatureCategoryAppl addedProductFeatureCategoryAppl, boolean success){
		this.addedProductFeatureCategoryAppl = addedProductFeatureCategoryAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureCategoryAppl getAddedProductFeatureCategoryAppl() {
		return addedProductFeatureCategoryAppl;
	}

}
