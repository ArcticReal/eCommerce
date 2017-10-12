package com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.event.SegmentGroupClassificationUpdated;
import com.skytala.eCommerce.domain.marketing.relations.segmentGroupClassification.model.SegmentGroupClassification;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSegmentGroupClassification extends Command {

private SegmentGroupClassification elementToBeUpdated;

public UpdateSegmentGroupClassification(SegmentGroupClassification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SegmentGroupClassification getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SegmentGroupClassification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SegmentGroupClassification", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SegmentGroupClassification.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SegmentGroupClassification.class);
}
success = false;
}
Event resultingEvent = new SegmentGroupClassificationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
