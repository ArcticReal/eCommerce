package com.skytala.eCommerce.domain.content.relations.contentRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentRole.model.ContentRole;
public class ContentRoleFound implements Event{

	private List<ContentRole> contentRoles;

	public ContentRoleFound(List<ContentRole> contentRoles) {
		this.contentRoles = contentRoles;
	}

	public List<ContentRole> getContentRoles()	{
		return contentRoles;
	}

}
