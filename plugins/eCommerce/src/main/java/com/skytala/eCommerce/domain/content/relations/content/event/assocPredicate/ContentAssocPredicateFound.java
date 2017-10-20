package com.skytala.eCommerce.domain.content.relations.content.event.assocPredicate;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.assocPredicate.ContentAssocPredicate;
public class ContentAssocPredicateFound implements Event{

	private List<ContentAssocPredicate> contentAssocPredicates;

	public ContentAssocPredicateFound(List<ContentAssocPredicate> contentAssocPredicates) {
		this.contentAssocPredicates = contentAssocPredicates;
	}

	public List<ContentAssocPredicate> getContentAssocPredicates()	{
		return contentAssocPredicates;
	}

}
