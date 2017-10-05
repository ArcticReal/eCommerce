package com.skytala.eCommerce.domain.fixedAsset.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fixedAsset.model.FixedAsset;
public class FixedAssetFound implements Event{

	private List<FixedAsset> fixedAssets;

	public FixedAssetFound(List<FixedAsset> fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public List<FixedAsset> getFixedAssets()	{
		return fixedAssets;
	}

}
