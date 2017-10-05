package com.skytala.eCommerce.domain.mimeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.mimeType.model.MimeType;
public class MimeTypeAdded implements Event{

	private MimeType addedMimeType;
	private boolean success;

	public MimeTypeAdded(MimeType addedMimeType, boolean success){
		this.addedMimeType = addedMimeType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MimeType getAddedMimeType() {
		return addedMimeType;
	}

}
