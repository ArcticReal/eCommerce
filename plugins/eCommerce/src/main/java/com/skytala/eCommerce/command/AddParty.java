package com.skytala.eCommerce.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Party;

public class AddParty implements Command {

	private Party elementToBeAdded;

	public AddParty(Party elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		try {
			elementToBeAdded.setPartyId(delegator.getNextSeqId("Party"));
			GenericValue newValue = delegator.makeValue("Party", elementToBeAdded.mapAttributeField());
			delegator.create(newValue);
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}

		Broker.instance().publish(new PartyAdded(success));
	}
}
