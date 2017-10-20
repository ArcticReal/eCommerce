package com.skytala.eCommerce.domain.content.relations.content.event.assocPredicate;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.assocPredicate.ContentAssocPredicate;
public class ContentAssocPredicateAdded implements Event{

	private ContentAssocPredicate addedContentAssocPredicate;
	private boolean success;

	public ContentAssocPredicateAdded(ContentAssocPredicate addedContentAssocPredicate, boolean success){
		this.addedContentAssocPredicate = addedContentAssocPredicate;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentAssocPredicate getAddedContentAssocPredicate() {
		return addedContentAssocPredicate;
	}

}
