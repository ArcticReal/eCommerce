package com.skytala.eCommerce.domain.party.relations.partyGeoPoint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.event.PartyGeoPointAdded;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.mapper.PartyGeoPointMapper;
import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.model.PartyGeoPoint;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyGeoPoint extends Command {

private PartyGeoPoint elementToBeAdded;
public AddPartyGeoPoint(PartyGeoPoint elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyGeoPoint addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyGeoPoint", elementToBeAdded.mapAttributeField());
addedElement = PartyGeoPointMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyGeoPointAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
