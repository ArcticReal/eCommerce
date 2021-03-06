package com.skytala.eCommerce.domain.content.relations.fileExtension.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.fileExtension.model.FileExtension;
public class FileExtensionFound implements Event{

	private List<FileExtension> fileExtensions;

	public FileExtensionFound(List<FileExtension> fileExtensions) {
		this.fileExtensions = fileExtensions;
	}

	public List<FileExtension> getFileExtensions()	{
		return fileExtensions;
	}

}
