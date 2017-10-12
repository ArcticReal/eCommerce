package com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.model.MimeTypeHtmlTemplate;
public class MimeTypeHtmlTemplateFound implements Event{

	private List<MimeTypeHtmlTemplate> mimeTypeHtmlTemplates;

	public MimeTypeHtmlTemplateFound(List<MimeTypeHtmlTemplate> mimeTypeHtmlTemplates) {
		this.mimeTypeHtmlTemplates = mimeTypeHtmlTemplates;
	}

	public List<MimeTypeHtmlTemplate> getMimeTypeHtmlTemplates()	{
		return mimeTypeHtmlTemplates;
	}

}
