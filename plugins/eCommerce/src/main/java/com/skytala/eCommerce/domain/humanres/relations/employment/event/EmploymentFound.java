package com.skytala.eCommerce.domain.humanres.relations.employment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.employment.model.Employment;
public class EmploymentFound implements Event{

	private List<Employment> employments;

	public EmploymentFound(List<Employment> employments) {
		this.employments = employments;
	}

	public List<Employment> getEmployments()	{
		return employments;
	}

}
