package com.skytala.eCommerce.domain.content.relations.survey.event.questionOption;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.questionOption.SurveyQuestionOption;
public class SurveyQuestionOptionUpdated implements Event{

	private boolean success;

	public SurveyQuestionOptionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
