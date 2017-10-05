package com.skytala.eCommerce.domain.reorderGuideline.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.reorderGuideline.event.ReorderGuidelineUpdated;
import com.skytala.eCommerce.domain.reorderGuideline.model.ReorderGuideline;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReorderGuideline extends Command {

private ReorderGuideline elementToBeUpdated;

public UpdateReorderGuideline(ReorderGuideline elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReorderGuideline getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReorderGuideline elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReorderGuideline", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReorderGuideline.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReorderGuideline.class);
}
success = false;
}
Event resultingEvent = new ReorderGuidelineUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
