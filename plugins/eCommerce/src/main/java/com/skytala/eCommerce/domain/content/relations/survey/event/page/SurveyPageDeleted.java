package com.skytala.eCommerce.domain.content.relations.survey.event.page;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.page.SurveyPage;
public class SurveyPageDeleted implements Event{

	private boolean success;

	public SurveyPageDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
