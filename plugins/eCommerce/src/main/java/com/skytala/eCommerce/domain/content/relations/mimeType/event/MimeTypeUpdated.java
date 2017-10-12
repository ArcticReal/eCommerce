package com.skytala.eCommerce.domain.content.relations.mimeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.mimeType.model.MimeType;
public class MimeTypeUpdated implements Event{

	private boolean success;

	public MimeTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
