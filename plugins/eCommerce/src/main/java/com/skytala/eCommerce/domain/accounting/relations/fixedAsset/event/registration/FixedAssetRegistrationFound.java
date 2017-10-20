package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.registration;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.registration.FixedAssetRegistration;
public class FixedAssetRegistrationFound implements Event{

	private List<FixedAssetRegistration> fixedAssetRegistrations;

	public FixedAssetRegistrationFound(List<FixedAssetRegistration> fixedAssetRegistrations) {
		this.fixedAssetRegistrations = fixedAssetRegistrations;
	}

	public List<FixedAssetRegistration> getFixedAssetRegistrations()	{
		return fixedAssetRegistrations;
	}

}
