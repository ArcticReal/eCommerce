package com.skytala.eCommerce.domain.content.relations.survey.event.page;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.page.SurveyPage;
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
