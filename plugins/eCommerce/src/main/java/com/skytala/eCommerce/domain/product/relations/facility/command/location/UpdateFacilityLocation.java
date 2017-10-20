package com.skytala.eCommerce.domain.product.relations.facility.command.location;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facility.event.location.FacilityLocationUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.model.location.FacilityLocation;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityLocation extends Command {

private FacilityLocation elementToBeUpdated;

public UpdateFacilityLocation(FacilityLocation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityLocation getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityLocation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityLocation", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityLocation.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityLocation.class);
}
success = false;
}
Event resultingEvent = new FacilityLocationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
