package com.skytala.eCommerce.domain.content.relations.mimeType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.mimeType.model.MimeType;
public class MimeTypeFound implements Event{

	private List<MimeType> mimeTypes;

	public MimeTypeFound(List<MimeType> mimeTypes) {
		this.mimeTypes = mimeTypes;
	}

	public List<MimeType> getMimeTypes()	{
		return mimeTypes;
	}

}
