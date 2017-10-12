package com.skytala.eCommerce.domain.content.relations.webSiteRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSiteRole.model.WebSiteRole;
public class WebSiteRoleFound implements Event{

	private List<WebSiteRole> webSiteRoles;

	public WebSiteRoleFound(List<WebSiteRole> webSiteRoles) {
		this.webSiteRoles = webSiteRoles;
	}

	public List<WebSiteRole> getWebSiteRoles()	{
		return webSiteRoles;
	}

}
