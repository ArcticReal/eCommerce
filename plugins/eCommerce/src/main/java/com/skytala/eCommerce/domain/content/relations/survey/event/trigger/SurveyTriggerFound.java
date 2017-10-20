package com.skytala.eCommerce.domain.content.relations.survey.event.trigger;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.survey.model.trigger.SurveyTrigger;
public class SurveyTriggerFound implements Event{

	private List<SurveyTrigger> surveyTriggers;

	public SurveyTriggerFound(List<SurveyTrigger> surveyTriggers) {
		this.surveyTriggers = surveyTriggers;
	}

	public List<SurveyTrigger> getSurveyTriggers()	{
		return surveyTriggers;
	}

}
