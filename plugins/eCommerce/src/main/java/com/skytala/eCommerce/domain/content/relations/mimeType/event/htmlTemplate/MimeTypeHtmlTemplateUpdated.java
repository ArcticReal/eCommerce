package com.skytala.eCommerce.domain.content.relations.mimeType.event.htmlTemplate;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.mimeType.model.htmlTemplate.MimeTypeHtmlTemplate;
public class MimeTypeHtmlTemplateUpdated implements Event{

	private boolean success;

	public MimeTypeHtmlTemplateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
