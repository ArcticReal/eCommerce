package com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model.WebSitePathAlias;
public class WebSitePathAliasFound implements Event{

	private List<WebSitePathAlias> webSitePathAliass;

	public WebSitePathAliasFound(List<WebSitePathAlias> webSitePathAliass) {
		this.webSitePathAliass = webSitePathAliass;
	}

	public List<WebSitePathAlias> getWebSitePathAliass()	{
		return webSitePathAliass;
	}

}
