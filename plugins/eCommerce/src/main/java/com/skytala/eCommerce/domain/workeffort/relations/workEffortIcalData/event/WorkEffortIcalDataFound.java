package com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.workEffortIcalData.model.WorkEffortIcalData;
public class WorkEffortIcalDataFound implements Event{

	private List<WorkEffortIcalData> workEffortIcalDatas;

	public WorkEffortIcalDataFound(List<WorkEffortIcalData> workEffortIcalDatas) {
		this.workEffortIcalDatas = workEffortIcalDatas;
	}

	public List<WorkEffortIcalData> getWorkEffortIcalDatas()	{
		return workEffortIcalDatas;
	}

}
