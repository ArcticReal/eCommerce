package com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.model.FixedAssetRegistration;
public class FixedAssetRegistrationFound implements Event{

	private List<FixedAssetRegistration> fixedAssetRegistrations;

	public FixedAssetRegistrationFound(List<FixedAssetRegistration> fixedAssetRegistrations) {
		this.fixedAssetRegistrations = fixedAssetRegistrations;
	}

	public List<FixedAssetRegistration> getFixedAssetRegistrations()	{
		return fixedAssetRegistrations;
	}

}
