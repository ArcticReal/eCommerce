package com.skytala.eCommerce.domain.content.relations.fileExtension.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.fileExtension.model.FileExtension;
public class FileExtensionAdded implements Event{

	private FileExtension addedFileExtension;
	private boolean success;

	public FileExtensionAdded(FileExtension addedFileExtension, boolean success){
		this.addedFileExtension = addedFileExtension;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public FileExtension getAddedFileExtension() {
		return addedFileExtension;
	}

}
