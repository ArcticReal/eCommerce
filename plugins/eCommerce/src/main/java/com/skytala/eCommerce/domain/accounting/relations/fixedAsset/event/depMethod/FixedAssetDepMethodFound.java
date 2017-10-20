package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.depMethod;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.depMethod.FixedAssetDepMethod;
public class FixedAssetDepMethodFound implements Event{

	private List<FixedAssetDepMethod> fixedAssetDepMethods;

	public FixedAssetDepMethodFound(List<FixedAssetDepMethod> fixedAssetDepMethods) {
		this.fixedAssetDepMethods = fixedAssetDepMethods;
	}

	public List<FixedAssetDepMethod> getFixedAssetDepMethods()	{
		return fixedAssetDepMethods;
	}

}
