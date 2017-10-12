package com.skytala.eCommerce.domain.content.relations.contentRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentRole.model.ContentRole;
public class ContentRoleAdded implements Event{

	private ContentRole addedContentRole;
	private boolean success;

	public ContentRoleAdded(ContentRole addedContentRole, boolean success){
		this.addedContentRole = addedContentRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentRole getAddedContentRole() {
		return addedContentRole;
	}

}
