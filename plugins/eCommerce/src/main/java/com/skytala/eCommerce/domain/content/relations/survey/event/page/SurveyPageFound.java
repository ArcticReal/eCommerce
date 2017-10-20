package com.skytala.eCommerce.domain.content.relations.survey.event.page;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.page.SurveyPage;
public class SurveyPageFound implements Event{

	private List<SurveyPage> surveyPages;

	public SurveyPageFound(List<SurveyPage> surveyPages) {
		this.surveyPages = surveyPages;
	}

	public List<SurveyPage> getSurveyPages()	{
		return surveyPages;
	}

}
