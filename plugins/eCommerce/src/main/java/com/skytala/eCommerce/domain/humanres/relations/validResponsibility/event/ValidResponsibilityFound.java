package com.skytala.eCommerce.domain.humanres.relations.validResponsibility.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model.ValidResponsibility;
public class ValidResponsibilityFound implements Event{

	private List<ValidResponsibility> validResponsibilitys;

	public ValidResponsibilityFound(List<ValidResponsibility> validResponsibilitys) {
		this.validResponsibilitys = validResponsibilitys;
	}

	public List<ValidResponsibility> getValidResponsibilitys()	{
		return validResponsibilitys;
	}

}
