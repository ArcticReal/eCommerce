package com.skytala.eCommerce.domain.accommodationMapType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accommodationMapType.event.AccommodationMapTypeUpdated;
import com.skytala.eCommerce.domain.accommodationMapType.model.AccommodationMapType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAccommodationMapType extends Command {

private AccommodationMapType elementToBeUpdated;

public UpdateAccommodationMapType(AccommodationMapType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AccommodationMapType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AccommodationMapType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AccommodationMapType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AccommodationMapType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AccommodationMapType.class);
}
success = false;
}
Event resultingEvent = new AccommodationMapTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
