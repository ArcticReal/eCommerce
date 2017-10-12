package com.skytala.eCommerce.domain.accounting.relations.partyRate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.event.PartyRateAdded;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.mapper.PartyRateMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.model.PartyRate;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyRate extends Command {

private PartyRate elementToBeAdded;
public AddPartyRate(PartyRate elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyRate addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyRate", elementToBeAdded.mapAttributeField());
addedElement = PartyRateMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyRateAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
