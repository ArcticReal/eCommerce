package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeAttr.FixedAssetTypeAttr;
public class FixedAssetTypeAttrAdded implements Event{

	private FixedAssetTypeAttr addedFixedAssetTypeAttr;
	private boolean success;

	public FixedAssetTypeAttrAdded(FixedAssetTypeAttr addedFixedAssetTypeAttr, boolean success){
		this.addedFixedAssetTypeAttr = addedFixedAssetTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetTypeAttr getAddedFixedAssetTypeAttr() {
		return addedFixedAssetTypeAttr;
	}

}
