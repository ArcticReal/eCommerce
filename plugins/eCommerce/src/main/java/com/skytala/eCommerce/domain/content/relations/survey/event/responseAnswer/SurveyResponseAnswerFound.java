package com.skytala.eCommerce.domain.content.relations.survey.event.responseAnswer;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.responseAnswer.SurveyResponseAnswer;
public class SurveyResponseAnswerFound implements Event{

	private List<SurveyResponseAnswer> surveyResponseAnswers;

	public SurveyResponseAnswerFound(List<SurveyResponseAnswer> surveyResponseAnswers) {
		this.surveyResponseAnswers = surveyResponseAnswers;
	}

	public List<SurveyResponseAnswer> getSurveyResponseAnswers()	{
		return surveyResponseAnswers;
	}

}
