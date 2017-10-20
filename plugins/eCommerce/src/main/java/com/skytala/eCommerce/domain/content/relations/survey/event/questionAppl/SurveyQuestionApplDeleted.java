package com.skytala.eCommerce.domain.content.relations.survey.event.questionAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.questionAppl.SurveyQuestionAppl;
public class SurveyQuestionApplDeleted implements Event{

	private boolean success;

	public SurveyQuestionApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
