package com.skytala.eCommerce.domain.mimeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.mimeType.model.MimeType;
public class MimeTypeUpdated implements Event{

	private boolean success;

	public MimeTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
