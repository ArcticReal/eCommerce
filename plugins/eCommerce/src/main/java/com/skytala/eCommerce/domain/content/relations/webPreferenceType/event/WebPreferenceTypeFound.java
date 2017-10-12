package com.skytala.eCommerce.domain.content.relations.webPreferenceType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webPreferenceType.model.WebPreferenceType;
public class WebPreferenceTypeFound implements Event{

	private List<WebPreferenceType> webPreferenceTypes;

	public WebPreferenceTypeFound(List<WebPreferenceType> webPreferenceTypes) {
		this.webPreferenceTypes = webPreferenceTypes;
	}

	public List<WebPreferenceType> getWebPreferenceTypes()	{
		return webPreferenceTypes;
	}

}
