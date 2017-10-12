package com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.model.SurveyQuestionCategory;
public class SurveyQuestionCategoryUpdated implements Event{

	private boolean success;

	public SurveyQuestionCategoryUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
