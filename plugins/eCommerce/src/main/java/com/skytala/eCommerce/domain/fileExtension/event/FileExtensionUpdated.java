package com.skytala.eCommerce.domain.fileExtension.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.fileExtension.model.FileExtension;
public class FileExtensionUpdated implements Event{

	private boolean success;

	public FileExtensionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
