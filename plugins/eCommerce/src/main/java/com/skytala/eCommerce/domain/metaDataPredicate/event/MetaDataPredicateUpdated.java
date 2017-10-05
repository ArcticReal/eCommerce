package com.skytala.eCommerce.domain.metaDataPredicate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.metaDataPredicate.model.MetaDataPredicate;
public class MetaDataPredicateUpdated implements Event{

	private boolean success;

	public MetaDataPredicateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
