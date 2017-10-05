package com.skytala.eCommerce.domain.surveyQuestion.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.surveyQuestion.model.SurveyQuestion;
public class SurveyQuestionAdded implements Event{

	private SurveyQuestion addedSurveyQuestion;
	private boolean success;

	public SurveyQuestionAdded(SurveyQuestion addedSurveyQuestion, boolean success){
		this.addedSurveyQuestion = addedSurveyQuestion;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyQuestion getAddedSurveyQuestion() {
		return addedSurveyQuestion;
	}

}
