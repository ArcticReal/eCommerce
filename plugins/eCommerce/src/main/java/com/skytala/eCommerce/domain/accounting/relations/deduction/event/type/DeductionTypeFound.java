package com.skytala.eCommerce.domain.accounting.relations.deduction.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.deduction.model.type.DeductionType;
public class DeductionTypeFound implements Event{

	private List<DeductionType> deductionTypes;

	public DeductionTypeFound(List<DeductionType> deductionTypes) {
		this.deductionTypes = deductionTypes;
	}

	public List<DeductionType> getDeductionTypes()	{
		return deductionTypes;
	}

}
