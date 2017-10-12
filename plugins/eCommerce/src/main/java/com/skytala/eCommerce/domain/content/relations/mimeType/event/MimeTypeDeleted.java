package com.skytala.eCommerce.domain.content.relations.mimeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.mimeType.model.MimeType;
public class MimeTypeDeleted implements Event{

	private boolean success;

	public MimeTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
