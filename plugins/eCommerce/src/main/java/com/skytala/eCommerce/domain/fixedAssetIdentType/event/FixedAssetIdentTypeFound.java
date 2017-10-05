package com.skytala.eCommerce.domain.fixedAssetIdentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fixedAssetIdentType.model.FixedAssetIdentType;
public class FixedAssetIdentTypeFound implements Event{

	private List<FixedAssetIdentType> fixedAssetIdentTypes;

	public FixedAssetIdentTypeFound(List<FixedAssetIdentType> fixedAssetIdentTypes) {
		this.fixedAssetIdentTypes = fixedAssetIdentTypes;
	}

	public List<FixedAssetIdentType> getFixedAssetIdentTypes()	{
		return fixedAssetIdentTypes;
	}

}
