package com.skytala.eCommerce.domain.content.relations.survey.event.response;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.response.SurveyResponse;
public class SurveyResponseFound implements Event{

	private List<SurveyResponse> surveyResponses;

	public SurveyResponseFound(List<SurveyResponse> surveyResponses) {
		this.surveyResponses = surveyResponses;
	}

	public List<SurveyResponse> getSurveyResponses()	{
		return surveyResponses;
	}

}
