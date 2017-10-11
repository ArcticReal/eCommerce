package com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.event.FacilityGroupRollupUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.model.FacilityGroupRollup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityGroupRollup extends Command {

private FacilityGroupRollup elementToBeUpdated;

public UpdateFacilityGroupRollup(FacilityGroupRollup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityGroupRollup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityGroupRollup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityGroupRollup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityGroupRollup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityGroupRollup.class);
}
success = false;
}
Event resultingEvent = new FacilityGroupRollupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
