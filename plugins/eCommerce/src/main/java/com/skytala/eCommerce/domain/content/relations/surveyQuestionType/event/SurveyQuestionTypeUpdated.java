package com.skytala.eCommerce.domain.content.relations.surveyQuestionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionType.model.SurveyQuestionType;
public class SurveyQuestionTypeUpdated implements Event{

	private boolean success;

	public SurveyQuestionTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
