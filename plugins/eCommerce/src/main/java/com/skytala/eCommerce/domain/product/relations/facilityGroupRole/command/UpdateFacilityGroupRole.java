package com.skytala.eCommerce.domain.product.relations.facilityGroupRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.event.FacilityGroupRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.model.FacilityGroupRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityGroupRole extends Command {

private FacilityGroupRole elementToBeUpdated;

public UpdateFacilityGroupRole(FacilityGroupRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityGroupRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityGroupRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityGroupRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityGroupRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityGroupRole.class);
}
success = false;
}
Event resultingEvent = new FacilityGroupRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
