package com.skytala.eCommerce.domain.content.relations.survey.event.page;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.page.SurveyPage;
public class SurveyPageUpdated implements Event{

	private boolean success;

	public SurveyPageUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
