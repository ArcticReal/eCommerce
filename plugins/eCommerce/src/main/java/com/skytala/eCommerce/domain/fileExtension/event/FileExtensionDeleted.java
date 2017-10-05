package com.skytala.eCommerce.domain.fileExtension.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fileExtension.model.FileExtension;
public class FileExtensionDeleted implements Event{

	private boolean success;

	public FileExtensionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
