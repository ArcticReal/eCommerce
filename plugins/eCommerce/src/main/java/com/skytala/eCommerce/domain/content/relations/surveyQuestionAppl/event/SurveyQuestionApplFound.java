package com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyQuestionAppl.model.SurveyQuestionAppl;
public class SurveyQuestionApplFound implements Event{

	private List<SurveyQuestionAppl> surveyQuestionAppls;

	public SurveyQuestionApplFound(List<SurveyQuestionAppl> surveyQuestionAppls) {
		this.surveyQuestionAppls = surveyQuestionAppls;
	}

	public List<SurveyQuestionAppl> getSurveyQuestionAppls()	{
		return surveyQuestionAppls;
	}

}
