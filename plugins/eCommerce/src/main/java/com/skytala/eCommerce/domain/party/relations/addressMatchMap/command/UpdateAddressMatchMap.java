package com.skytala.eCommerce.domain.party.relations.addressMatchMap.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.event.AddressMatchMapUpdated;
import com.skytala.eCommerce.domain.party.relations.addressMatchMap.model.AddressMatchMap;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAddressMatchMap extends Command {

private AddressMatchMap elementToBeUpdated;

public UpdateAddressMatchMap(AddressMatchMap elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AddressMatchMap getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AddressMatchMap elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AddressMatchMap", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AddressMatchMap.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AddressMatchMap.class);
}
success = false;
}
Event resultingEvent = new AddressMatchMapUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
