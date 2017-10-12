package com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetProduct.model.FixedAssetProduct;
public class FixedAssetProductAdded implements Event{

	private FixedAssetProduct addedFixedAssetProduct;
	private boolean success;

	public FixedAssetProductAdded(FixedAssetProduct addedFixedAssetProduct, boolean success){
		this.addedFixedAssetProduct = addedFixedAssetProduct;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetProduct getAddedFixedAssetProduct() {
		return addedFixedAssetProduct;
	}

}
