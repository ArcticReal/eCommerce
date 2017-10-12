package com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortKeyword.model.WorkEffortKeyword;
public class WorkEffortKeywordFound implements Event{

	private List<WorkEffortKeyword> workEffortKeywords;

	public WorkEffortKeywordFound(List<WorkEffortKeyword> workEffortKeywords) {
		this.workEffortKeywords = workEffortKeywords;
	}

	public List<WorkEffortKeyword> getWorkEffortKeywords()	{
		return workEffortKeywords;
	}

}
