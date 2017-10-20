package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.registration;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.registration.FixedAssetRegistration;
public class FixedAssetRegistrationAdded implements Event{

	private FixedAssetRegistration addedFixedAssetRegistration;
	private boolean success;

	public FixedAssetRegistrationAdded(FixedAssetRegistration addedFixedAssetRegistration, boolean success){
		this.addedFixedAssetRegistration = addedFixedAssetRegistration;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FixedAssetRegistration getAddedFixedAssetRegistration() {
		return addedFixedAssetRegistration;
	}

}
