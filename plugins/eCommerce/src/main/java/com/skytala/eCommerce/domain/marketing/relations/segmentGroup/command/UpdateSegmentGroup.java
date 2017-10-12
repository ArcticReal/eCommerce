package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.SegmentGroupUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.SegmentGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSegmentGroup extends Command {

private SegmentGroup elementToBeUpdated;

public UpdateSegmentGroup(SegmentGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SegmentGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SegmentGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SegmentGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SegmentGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SegmentGroup.class);
}
success = false;
}
Event resultingEvent = new SegmentGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
