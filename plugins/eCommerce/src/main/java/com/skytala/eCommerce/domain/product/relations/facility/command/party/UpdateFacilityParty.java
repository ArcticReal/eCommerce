package com.skytala.eCommerce.domain.product.relations.facility.command.party;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.model.party.FacilityParty;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityParty extends Command {

private FacilityParty elementToBeUpdated;

public UpdateFacilityParty(FacilityParty elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityParty getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityParty elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityParty", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityParty.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityParty.class);
}
success = false;
}
Event resultingEvent = new FacilityPartyUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
