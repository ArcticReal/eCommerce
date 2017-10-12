package com.skytala.eCommerce.domain.marketing.relations.webSiteContactList.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.webSiteContactList.model.WebSiteContactList;
public class WebSiteContactListAdded implements Event{

	private WebSiteContactList addedWebSiteContactList;
	private boolean success;

	public WebSiteContactListAdded(WebSiteContactList addedWebSiteContactList, boolean success){
		this.addedWebSiteContactList = addedWebSiteContactList;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebSiteContactList getAddedWebSiteContactList() {
		return addedWebSiteContactList;
	}

}
