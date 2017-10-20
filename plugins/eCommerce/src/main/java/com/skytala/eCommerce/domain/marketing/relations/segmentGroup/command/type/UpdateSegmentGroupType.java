package com.skytala.eCommerce.domain.marketing.relations.segmentGroup.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.event.type.SegmentGroupTypeUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroup.model.type.SegmentGroupType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSegmentGroupType extends Command {

private SegmentGroupType elementToBeUpdated;

public UpdateSegmentGroupType(SegmentGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SegmentGroupType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SegmentGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SegmentGroupType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SegmentGroupType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SegmentGroupType.class);
}
success = false;
}
Event resultingEvent = new SegmentGroupTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
