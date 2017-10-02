package com.skytala.eCommerce.domain.party.command;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;

import com.skytala.eCommerce.domain.party.event.PartyAdded;
import com.skytala.eCommerce.domain.party.mapper.PartyMapper;
import com.skytala.eCommerce.domain.party.model.Party;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class AddParty extends Command {

	private Party elementToBeAdded;

	public AddParty(Party elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		boolean success;
		Party addedParty = null;
		try {
			elementToBeAdded.setPartyId(delegator.getNextSeqId("Party"));
			GenericValue newValue = delegator.makeValue("Party", elementToBeAdded.mapAttributeField());
			addedParty = PartyMapper.map(delegator.create(newValue));
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			success = false;
		}

		Event resultingEvent = new PartyAdded(addedParty, success);
		Broker.instance().publish(resultingEvent);
		return resultingEvent;
	}
}
