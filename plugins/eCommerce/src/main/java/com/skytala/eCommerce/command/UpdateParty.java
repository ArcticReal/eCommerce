package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Party;
import com.skytala.eCommerce.event.PartyUpdated;

public class UpdateParty implements Command {

	private Party elementToBeUpdated;

	public UpdateParty(Party elementToBeUpdated) {
		this.elementToBeUpdated = elementToBeUpdated;
	}

	public Party getElementToBeUpdated() {
		return elementToBeUpdated;
	}

	public void setElementToBeUpdated(Party elementToBeUpdated) {
		this.elementToBeUpdated = elementToBeUpdated;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			GenericValue newValue = delegator.makeValue("Party", elementToBeUpdated.mapAttributeField());
			delegator.store(newValue);
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}
		Broker.instance().publish(new PartyUpdated(success));
	}
}
