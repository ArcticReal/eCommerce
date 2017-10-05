package com.skytala.eCommerce.domain.surveyQuestionCategory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.surveyQuestionCategory.model.SurveyQuestionCategory;
public class SurveyQuestionCategoryFound implements Event{

	private List<SurveyQuestionCategory> surveyQuestionCategorys;

	public SurveyQuestionCategoryFound(List<SurveyQuestionCategory> surveyQuestionCategorys) {
		this.surveyQuestionCategorys = surveyQuestionCategorys;
	}

	public List<SurveyQuestionCategory> getSurveyQuestionCategorys()	{
		return surveyQuestionCategorys;
	}

}
