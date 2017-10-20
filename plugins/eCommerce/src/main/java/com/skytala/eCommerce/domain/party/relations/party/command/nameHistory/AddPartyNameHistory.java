package com.skytala.eCommerce.domain.party.relations.party.command.nameHistory;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.nameHistory.PartyNameHistoryAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.nameHistory.PartyNameHistoryMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.nameHistory.PartyNameHistory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyNameHistory extends Command {

private PartyNameHistory elementToBeAdded;
public AddPartyNameHistory(PartyNameHistory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyNameHistory addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyNameHistory", elementToBeAdded.mapAttributeField());
addedElement = PartyNameHistoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyNameHistoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
