package com.skytala.eCommerce.domain.accommodationClass.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accommodationClass.event.AccommodationClassUpdated;
import com.skytala.eCommerce.domain.accommodationClass.model.AccommodationClass;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAccommodationClass extends Command {

private AccommodationClass elementToBeUpdated;

public UpdateAccommodationClass(AccommodationClass elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AccommodationClass getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AccommodationClass elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AccommodationClass", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AccommodationClass.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AccommodationClass.class);
}
success = false;
}
Event resultingEvent = new AccommodationClassUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
