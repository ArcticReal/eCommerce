package com.skytala.eCommerce.domain.content.relations.mimeType.event.htmlTemplate;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.mimeType.model.htmlTemplate.MimeTypeHtmlTemplate;
public class MimeTypeHtmlTemplateAdded implements Event{

	private MimeTypeHtmlTemplate addedMimeTypeHtmlTemplate;
	private boolean success;

	public MimeTypeHtmlTemplateAdded(MimeTypeHtmlTemplate addedMimeTypeHtmlTemplate, boolean success){
		this.addedMimeTypeHtmlTemplate = addedMimeTypeHtmlTemplate;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public MimeTypeHtmlTemplate getAddedMimeTypeHtmlTemplate() {
		return addedMimeTypeHtmlTemplate;
	}

}
