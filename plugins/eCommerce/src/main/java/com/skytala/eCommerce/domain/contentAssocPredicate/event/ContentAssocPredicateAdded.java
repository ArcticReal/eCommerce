package com.skytala.eCommerce.domain.contentAssocPredicate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentAssocPredicate.model.ContentAssocPredicate;
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
