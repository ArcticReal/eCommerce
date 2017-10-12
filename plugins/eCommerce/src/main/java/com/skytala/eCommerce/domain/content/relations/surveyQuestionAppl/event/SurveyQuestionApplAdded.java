package com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.model.SurveyQuestionAppl;
public class SurveyQuestionApplAdded implements Event{

	private SurveyQuestionAppl addedSurveyQuestionAppl;
	private boolean success;

	public SurveyQuestionApplAdded(SurveyQuestionAppl addedSurveyQuestionAppl, boolean success){
		this.addedSurveyQuestionAppl = addedSurveyQuestionAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyQuestionAppl getAddedSurveyQuestionAppl() {
		return addedSurveyQuestionAppl;
	}

}
