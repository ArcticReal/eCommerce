package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeAttr.FixedAssetTypeAttr;
public class FixedAssetTypeAttrUpdated implements Event{

	private boolean success;

	public FixedAssetTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
