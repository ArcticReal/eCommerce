package com.skytala.eCommerce.domain.metaDataPredicate.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.metaDataPredicate.model.MetaDataPredicate;
public class MetaDataPredicateFound implements Event{

	private List<MetaDataPredicate> metaDataPredicates;

	public MetaDataPredicateFound(List<MetaDataPredicate> metaDataPredicates) {
		this.metaDataPredicates = metaDataPredicates;
	}

	public List<MetaDataPredicate> getMetaDataPredicates()	{
		return metaDataPredicates;
	}

}
