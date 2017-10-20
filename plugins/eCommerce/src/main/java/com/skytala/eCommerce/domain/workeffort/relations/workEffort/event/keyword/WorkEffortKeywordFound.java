package com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.keyword;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.keyword.WorkEffortKeyword;
public class WorkEffortKeywordFound implements Event{

	private List<WorkEffortKeyword> workEffortKeywords;

	public WorkEffortKeywordFound(List<WorkEffortKeyword> workEffortKeywords) {
		this.workEffortKeywords = workEffortKeywords;
	}

	public List<WorkEffortKeyword> getWorkEffortKeywords()	{
		return workEffortKeywords;
	}

}
