package com.skytala.eCommerce.domain.terminationReason.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.terminationReason.model.TerminationReason;
public class TerminationReasonFound implements Event{

	private List<TerminationReason> terminationReasons;

	public TerminationReasonFound(List<TerminationReason> terminationReasons) {
		this.terminationReasons = terminationReasons;
	}

	public List<TerminationReason> getTerminationReasons()	{
		return terminationReasons;
	}

}
