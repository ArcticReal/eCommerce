package com.skytala.eCommerce.domain.metaDataPredicate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.metaDataPredicate.model.MetaDataPredicate;
public class MetaDataPredicateDeleted implements Event{

	private boolean success;

	public MetaDataPredicateDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
