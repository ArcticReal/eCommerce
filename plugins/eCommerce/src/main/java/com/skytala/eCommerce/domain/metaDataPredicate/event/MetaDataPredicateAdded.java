package com.skytala.eCommerce.domain.metaDataPredicate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.metaDataPredicate.model.MetaDataPredicate;
public class MetaDataPredicateAdded implements Event{

	private MetaDataPredicate addedMetaDataPredicate;
	private boolean success;

	public MetaDataPredicateAdded(MetaDataPredicate addedMetaDataPredicate, boolean success){
		this.addedMetaDataPredicate = addedMetaDataPredicate;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MetaDataPredicate getAddedMetaDataPredicate() {
		return addedMetaDataPredicate;
	}

}
