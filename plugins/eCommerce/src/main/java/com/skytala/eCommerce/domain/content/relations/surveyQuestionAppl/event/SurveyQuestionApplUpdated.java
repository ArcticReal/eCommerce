package com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.model.SurveyQuestionAppl;
public class SurveyQuestionApplUpdated implements Event{

	private boolean success;

	public SurveyQuestionApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
