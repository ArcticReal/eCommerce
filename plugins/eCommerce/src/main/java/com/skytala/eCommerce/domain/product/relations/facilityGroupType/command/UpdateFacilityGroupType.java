package com.skytala.eCommerce.domain.product.relations.facilityGroupType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facilityGroupType.event.FacilityGroupTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityGroupType.model.FacilityGroupType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityGroupType extends Command {

private FacilityGroupType elementToBeUpdated;

public UpdateFacilityGroupType(FacilityGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityGroupType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityGroupType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityGroupType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityGroupType.class);
}
success = false;
}
Event resultingEvent = new FacilityGroupTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
