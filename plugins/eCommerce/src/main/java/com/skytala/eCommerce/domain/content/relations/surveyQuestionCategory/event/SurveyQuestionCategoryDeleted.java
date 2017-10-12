package com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionCategory.model.SurveyQuestionCategory;
public class SurveyQuestionCategoryDeleted implements Event{

	private boolean success;

	public SurveyQuestionCategoryDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
