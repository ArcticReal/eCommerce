package com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.event.FacilityContactMechPurposeUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.model.FacilityContactMechPurpose;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityContactMechPurpose extends Command {

private FacilityContactMechPurpose elementToBeUpdated;

public UpdateFacilityContactMechPurpose(FacilityContactMechPurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityContactMechPurpose getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityContactMechPurpose elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityContactMechPurpose", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityContactMechPurpose.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityContactMechPurpose.class);
}
success = false;
}
Event resultingEvent = new FacilityContactMechPurposeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
