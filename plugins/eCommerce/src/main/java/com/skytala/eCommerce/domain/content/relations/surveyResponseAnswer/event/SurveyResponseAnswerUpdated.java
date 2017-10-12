package com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyResponseAnswer.model.SurveyResponseAnswer;
public class SurveyResponseAnswerUpdated implements Event{

	private boolean success;

	public SurveyResponseAnswerUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
