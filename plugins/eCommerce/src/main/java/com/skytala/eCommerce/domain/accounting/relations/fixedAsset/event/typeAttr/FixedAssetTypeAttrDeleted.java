package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeAttr.FixedAssetTypeAttr;
public class FixedAssetTypeAttrDeleted implements Event{

	private boolean success;

	public FixedAssetTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
