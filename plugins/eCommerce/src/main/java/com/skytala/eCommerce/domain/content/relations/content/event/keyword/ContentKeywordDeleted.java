package com.skytala.eCommerce.domain.content.relations.content.event.keyword;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.keyword.ContentKeyword;
public class ContentKeywordDeleted implements Event{

	private boolean success;

	public ContentKeywordDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
