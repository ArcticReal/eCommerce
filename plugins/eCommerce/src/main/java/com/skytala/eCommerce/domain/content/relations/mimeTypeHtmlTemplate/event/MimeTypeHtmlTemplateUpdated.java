package com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.model.MimeTypeHtmlTemplate;
public class MimeTypeHtmlTemplateUpdated implements Event{

	private boolean success;

	public MimeTypeHtmlTemplateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
