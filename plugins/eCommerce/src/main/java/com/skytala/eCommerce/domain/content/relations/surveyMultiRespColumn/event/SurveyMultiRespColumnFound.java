package com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.surveyMultiRespColumn.model.SurveyMultiRespColumn;
public class SurveyMultiRespColumnFound implements Event{

	private List<SurveyMultiRespColumn> surveyMultiRespColumns;

	public SurveyMultiRespColumnFound(List<SurveyMultiRespColumn> surveyMultiRespColumns) {
		this.surveyMultiRespColumns = surveyMultiRespColumns;
	}

	public List<SurveyMultiRespColumn> getSurveyMultiRespColumns()	{
		return surveyMultiRespColumns;
	}

}
