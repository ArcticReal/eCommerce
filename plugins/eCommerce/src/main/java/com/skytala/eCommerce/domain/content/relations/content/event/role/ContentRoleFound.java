package com.skytala.eCommerce.domain.content.relations.content.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.role.ContentRole;
public class ContentRoleFound implements Event{

	private List<ContentRole> contentRoles;

	public ContentRoleFound(List<ContentRole> contentRoles) {
		this.contentRoles = contentRoles;
	}

	public List<ContentRole> getContentRoles()	{
		return contentRoles;
	}

}
