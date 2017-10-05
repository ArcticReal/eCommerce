package com.skytala.eCommerce.domain.survey.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.survey.model.Survey;
public class SurveyFound implements Event{

	private List<Survey> surveys;

	public SurveyFound(List<Survey> surveys) {
		this.surveys = surveys;
	}

	public List<Survey> getSurveys()	{
		return surveys;
	}

}
