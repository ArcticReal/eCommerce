package com.skytala.eCommerce.domain.content.relations.mimeType.event.htmlTemplate;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.mimeType.model.htmlTemplate.MimeTypeHtmlTemplate;
public class MimeTypeHtmlTemplateFound implements Event{

	private List<MimeTypeHtmlTemplate> mimeTypeHtmlTemplates;

	public MimeTypeHtmlTemplateFound(List<MimeTypeHtmlTemplate> mimeTypeHtmlTemplates) {
		this.mimeTypeHtmlTemplates = mimeTypeHtmlTemplates;
	}

	public List<MimeTypeHtmlTemplate> getMimeTypeHtmlTemplates()	{
		return mimeTypeHtmlTemplates;
	}

}
