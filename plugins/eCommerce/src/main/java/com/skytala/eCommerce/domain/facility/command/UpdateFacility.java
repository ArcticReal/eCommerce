package com.skytala.eCommerce.domain.facility.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.facility.event.FacilityUpdated;
import com.skytala.eCommerce.domain.facility.model.Facility;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacility extends Command {

private Facility elementToBeUpdated;

public UpdateFacility(Facility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Facility getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Facility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Facility", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Facility.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Facility.class);
}
success = false;
}
Event resultingEvent = new FacilityUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
