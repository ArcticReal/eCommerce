package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.ident;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.ident.FixedAssetIdent;
public class FixedAssetIdentFound implements Event{

	private List<FixedAssetIdent> fixedAssetIdents;

	public FixedAssetIdentFound(List<FixedAssetIdent> fixedAssetIdents) {
		this.fixedAssetIdents = fixedAssetIdents;
	}

	public List<FixedAssetIdent> getFixedAssetIdents()	{
		return fixedAssetIdents;
	}

}
