package com.skytala.eCommerce.domain.party.relations.partyContactMech.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyContactMech.event.PartyContactMechAdded;
import com.skytala.eCommerce.domain.party.relations.partyContactMech.mapper.PartyContactMechMapper;
import com.skytala.eCommerce.domain.party.relations.partyContactMech.model.PartyContactMech;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyContactMech extends Command {

private PartyContactMech elementToBeAdded;
public AddPartyContactMech(PartyContactMech elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyContactMech addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyContactMech", elementToBeAdded.mapAttributeField());
addedElement = PartyContactMechMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyContactMechAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
