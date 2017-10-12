package com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.event.SegmentGroupRoleUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupRole.model.SegmentGroupRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSegmentGroupRole extends Command {

private SegmentGroupRole elementToBeUpdated;

public UpdateSegmentGroupRole(SegmentGroupRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SegmentGroupRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SegmentGroupRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SegmentGroupRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SegmentGroupRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SegmentGroupRole.class);
}
success = false;
}
Event resultingEvent = new SegmentGroupRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
