package com.skytala.eCommerce.domain.party.relations.party.command.geoPoint;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.geoPoint.PartyGeoPointUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.geoPoint.PartyGeoPoint;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyGeoPoint extends Command {

private PartyGeoPoint elementToBeUpdated;

public UpdatePartyGeoPoint(PartyGeoPoint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyGeoPoint getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyGeoPoint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyGeoPoint", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyGeoPoint.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyGeoPoint.class);
}
success = false;
}
Event resultingEvent = new PartyGeoPointUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
