package com.skytala.eCommerce.domain.content.relations.survey.event.responseAnswer;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.responseAnswer.SurveyResponseAnswer;
public class SurveyResponseAnswerAdded implements Event{

	private SurveyResponseAnswer addedSurveyResponseAnswer;
	private boolean success;

	public SurveyResponseAnswerAdded(SurveyResponseAnswer addedSurveyResponseAnswer, boolean success){
		this.addedSurveyResponseAnswer = addedSurveyResponseAnswer;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SurveyResponseAnswer getAddedSurveyResponseAnswer() {
		return addedSurveyResponseAnswer;
	}

}
