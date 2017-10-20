package com.skytala.eCommerce.domain.content.relations.survey.event.question;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.question.SurveyQuestion;
public class SurveyQuestionDeleted implements Event{

	private boolean success;

	public SurveyQuestionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
