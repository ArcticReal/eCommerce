package com.skytala.eCommerce.domain.content.relations.surveyMultiResp.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyMultiResp.model.SurveyMultiResp;
public class SurveyMultiRespFound implements Event{

	private List<SurveyMultiResp> surveyMultiResps;

	public SurveyMultiRespFound(List<SurveyMultiResp> surveyMultiResps) {
		this.surveyMultiResps = surveyMultiResps;
	}

	public List<SurveyMultiResp> getSurveyMultiResps()	{
		return surveyMultiResps;
	}

}
