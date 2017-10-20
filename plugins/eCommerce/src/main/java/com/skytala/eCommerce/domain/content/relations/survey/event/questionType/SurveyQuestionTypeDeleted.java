package com.skytala.eCommerce.domain.content.relations.survey.event.questionType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.questionType.SurveyQuestionType;
public class SurveyQuestionTypeDeleted implements Event{

	private boolean success;

	public SurveyQuestionTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
