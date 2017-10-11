package com.skytala.eCommerce.domain.product.relations.facilityType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facilityType.event.FacilityTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityType.model.FacilityType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityType extends Command {

private FacilityType elementToBeUpdated;

public UpdateFacilityType(FacilityType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityType.class);
}
success = false;
}
Event resultingEvent = new FacilityTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
