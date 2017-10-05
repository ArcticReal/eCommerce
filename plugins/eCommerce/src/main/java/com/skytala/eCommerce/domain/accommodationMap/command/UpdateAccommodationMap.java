package com.skytala.eCommerce.domain.accommodationMap.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accommodationMap.event.AccommodationMapUpdated;
import com.skytala.eCommerce.domain.accommodationMap.model.AccommodationMap;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAccommodationMap extends Command {

private AccommodationMap elementToBeUpdated;

public UpdateAccommodationMap(AccommodationMap elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AccommodationMap getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AccommodationMap elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AccommodationMap", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AccommodationMap.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AccommodationMap.class);
}
success = false;
}
Event resultingEvent = new AccommodationMapUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
