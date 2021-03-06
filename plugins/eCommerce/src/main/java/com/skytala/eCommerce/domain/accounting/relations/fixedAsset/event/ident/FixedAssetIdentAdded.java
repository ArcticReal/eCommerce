package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.ident.FixedAssetIdent;
public class FixedAssetIdentAdded implements Event{

	private FixedAssetIdent addedFixedAssetIdent;
	private boolean success;

	public FixedAssetIdentAdded(FixedAssetIdent addedFixedAssetIdent, boolean success){
		this.addedFixedAssetIdent = addedFixedAssetIdent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetIdent getAddedFixedAssetIdent() {
		return addedFixedAssetIdent;
	}

}
