package com.skytala.eCommerce.domain.product.relations.product.event.featureCatGrpAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureCatGrpAppl.ProductFeatureCatGrpAppl;
public class ProductFeatureCatGrpApplAdded implements Event{

	private ProductFeatureCatGrpAppl addedProductFeatureCatGrpAppl;
	private boolean success;

	public ProductFeatureCatGrpApplAdded(ProductFeatureCatGrpAppl addedProductFeatureCatGrpAppl, boolean success){
		this.addedProductFeatureCatGrpAppl = addedProductFeatureCatGrpAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureCatGrpAppl getAddedProductFeatureCatGrpAppl() {
		return addedProductFeatureCatGrpAppl;
	}

}
