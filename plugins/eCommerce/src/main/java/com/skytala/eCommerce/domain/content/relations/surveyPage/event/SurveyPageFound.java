package com.skytala.eCommerce.domain.content.relations.surveyPage.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyPage.model.SurveyPage;
public class SurveyPageFound implements Event{

	private List<SurveyPage> surveyPages;

	public SurveyPageFound(List<SurveyPage> surveyPages) {
		this.surveyPages = surveyPages;
	}

	public List<SurveyPage> getSurveyPages()	{
		return surveyPages;
	}

}
