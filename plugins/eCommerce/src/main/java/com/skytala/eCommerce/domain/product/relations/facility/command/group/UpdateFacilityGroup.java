package com.skytala.eCommerce.domain.product.relations.facility.command.group;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facility.event.group.FacilityGroupUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.model.group.FacilityGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityGroup extends Command {

private FacilityGroup elementToBeUpdated;

public UpdateFacilityGroup(FacilityGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityGroup.class);
}
success = false;
}
Event resultingEvent = new FacilityGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
