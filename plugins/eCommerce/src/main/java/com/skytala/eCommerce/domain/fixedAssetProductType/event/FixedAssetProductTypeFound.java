package com.skytala.eCommerce.domain.fixedAssetProductType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fixedAssetProductType.model.FixedAssetProductType;
public class FixedAssetProductTypeFound implements Event{

	private List<FixedAssetProductType> fixedAssetProductTypes;

	public FixedAssetProductTypeFound(List<FixedAssetProductType> fixedAssetProductTypes) {
		this.fixedAssetProductTypes = fixedAssetProductTypes;
	}

	public List<FixedAssetProductType> getFixedAssetProductTypes()	{
		return fixedAssetProductTypes;
	}

}
