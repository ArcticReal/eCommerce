package com.skytala.eCommerce.domain.content.relations.survey.event.questionOption;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.questionOption.SurveyQuestionOption;
public class SurveyQuestionOptionDeleted implements Event{

	private boolean success;

	public SurveyQuestionOptionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
