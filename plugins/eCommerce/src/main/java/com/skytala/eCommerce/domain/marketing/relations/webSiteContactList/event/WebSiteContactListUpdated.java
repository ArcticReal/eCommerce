package com.skytala.eCommerce.domain.marketing.relations.webSiteContactList.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.marketing.relations.webSiteContactList.model.WebSiteContactList;
public class WebSiteContactListUpdated implements Event{

	private boolean success;

	public WebSiteContactListUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
