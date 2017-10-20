package com.skytala.eCommerce.domain.marketing.relations.contactList.event.webSite;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.contactList.model.webSite.WebSiteContactList;
public class WebSiteContactListFound implements Event{

	private List<WebSiteContactList> webSiteContactLists;

	public WebSiteContactListFound(List<WebSiteContactList> webSiteContactLists) {
		this.webSiteContactLists = webSiteContactLists;
	}

	public List<WebSiteContactList> getWebSiteContactLists()	{
		return webSiteContactLists;
	}

}
