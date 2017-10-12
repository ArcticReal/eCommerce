package com.skytala.eCommerce.domain.content.relations.surveyPage.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyPage.model.SurveyPage;
public class SurveyPageAdded implements Event{

	private SurveyPage addedSurveyPage;
	private boolean success;

	public SurveyPageAdded(SurveyPage addedSurveyPage, boolean success){
		this.addedSurveyPage = addedSurveyPage;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyPage getAddedSurveyPage() {
		return addedSurveyPage;
	}

}
