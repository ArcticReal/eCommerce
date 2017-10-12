package com.skytala.eCommerce.domain.content.relations.contentAssocPredicate.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentAssocPredicate.model.ContentAssocPredicate;
public class ContentAssocPredicateFound implements Event{

	private List<ContentAssocPredicate> contentAssocPredicates;

	public ContentAssocPredicateFound(List<ContentAssocPredicate> contentAssocPredicates) {
		this.contentAssocPredicates = contentAssocPredicates;
	}

	public List<ContentAssocPredicate> getContentAssocPredicates()	{
		return contentAssocPredicates;
	}

}
