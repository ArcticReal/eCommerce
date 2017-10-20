package com.skytala.eCommerce.domain.content.relations.survey.event.multiRespColumn;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.multiRespColumn.SurveyMultiRespColumn;
public class SurveyMultiRespColumnFound implements Event{

	private List<SurveyMultiRespColumn> surveyMultiRespColumns;

	public SurveyMultiRespColumnFound(List<SurveyMultiRespColumn> surveyMultiRespColumns) {
		this.surveyMultiRespColumns = surveyMultiRespColumns;
	}

	public List<SurveyMultiRespColumn> getSurveyMultiRespColumns()	{
		return surveyMultiRespColumns;
	}

}
