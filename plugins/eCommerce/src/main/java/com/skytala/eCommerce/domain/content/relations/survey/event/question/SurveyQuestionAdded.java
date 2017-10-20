package com.skytala.eCommerce.domain.content.relations.survey.event.question;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.question.SurveyQuestion;
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
