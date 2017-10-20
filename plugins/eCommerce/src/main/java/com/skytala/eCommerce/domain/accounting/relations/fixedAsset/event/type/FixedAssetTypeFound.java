package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.type.FixedAssetType;
public class FixedAssetTypeFound implements Event{

	private List<FixedAssetType> fixedAssetTypes;

	public FixedAssetTypeFound(List<FixedAssetType> fixedAssetTypes) {
		this.fixedAssetTypes = fixedAssetTypes;
	}

	public List<FixedAssetType> getFixedAssetTypes()	{
		return fixedAssetTypes;
	}

}
