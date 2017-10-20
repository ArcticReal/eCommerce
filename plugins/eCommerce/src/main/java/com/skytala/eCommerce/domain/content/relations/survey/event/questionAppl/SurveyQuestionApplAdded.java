package com.skytala.eCommerce.domain.content.relations.survey.event.questionAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.questionAppl.SurveyQuestionAppl;
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
