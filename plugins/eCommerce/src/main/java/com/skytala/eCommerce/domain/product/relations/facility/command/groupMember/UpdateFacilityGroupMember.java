package com.skytala.eCommerce.domain.product.relations.facility.command.groupMember;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facility.event.groupMember.FacilityGroupMemberUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.model.groupMember.FacilityGroupMember;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityGroupMember extends Command {

private FacilityGroupMember elementToBeUpdated;

public UpdateFacilityGroupMember(FacilityGroupMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityGroupMember getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityGroupMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityGroupMember", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityGroupMember.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityGroupMember.class);
}
success = false;
}
Event resultingEvent = new FacilityGroupMemberUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
