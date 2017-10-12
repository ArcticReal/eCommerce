package com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.model.ContentSearchConstraint;
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
