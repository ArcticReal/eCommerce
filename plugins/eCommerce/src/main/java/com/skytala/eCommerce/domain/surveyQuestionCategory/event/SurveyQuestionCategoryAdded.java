package com.skytala.eCommerce.domain.surveyQuestionCategory.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.surveyQuestionCategory.model.SurveyQuestionCategory;
public class SurveyQuestionCategoryAdded implements Event{

	private SurveyQuestionCategory addedSurveyQuestionCategory;
	private boolean success;

	public SurveyQuestionCategoryAdded(SurveyQuestionCategory addedSurveyQuestionCategory, boolean success){
		this.addedSurveyQuestionCategory = addedSurveyQuestionCategory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyQuestionCategory getAddedSurveyQuestionCategory() {
		return addedSurveyQuestionCategory;
	}

}
