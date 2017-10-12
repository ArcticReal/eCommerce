package com.skytala.eCommerce.domain.content.relations.metaDataPredicate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.model.MetaDataPredicate;
public class MetaDataPredicateUpdated implements Event{

	private boolean success;

	public MetaDataPredicateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
