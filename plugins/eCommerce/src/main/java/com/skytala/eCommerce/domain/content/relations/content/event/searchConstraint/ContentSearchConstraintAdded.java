package com.skytala.eCommerce.domain.content.relations.content.event.searchConstraint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.searchConstraint.ContentSearchConstraint;
public class ContentSearchConstraintAdded implements Event{

	private ContentSearchConstraint addedContentSearchConstraint;
	private boolean success;

	public ContentSearchConstraintAdded(ContentSearchConstraint addedContentSearchConstraint, boolean success){
		this.addedContentSearchConstraint = addedContentSearchConstraint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentSearchConstraint getAddedContentSearchConstraint() {
		return addedContentSearchConstraint;
	}

}
