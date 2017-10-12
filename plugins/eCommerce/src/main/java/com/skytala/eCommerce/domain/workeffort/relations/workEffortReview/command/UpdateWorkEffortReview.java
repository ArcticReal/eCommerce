package com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.event.WorkEffortReviewUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.model.WorkEffortReview;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateWorkEffortReview extends Command {

private WorkEffortReview elementToBeUpdated;

public UpdateWorkEffortReview(WorkEffortReview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public WorkEffortReview getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(WorkEffortReview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("WorkEffortReview", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(WorkEffortReview.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(WorkEffortReview.class);
}
success = false;
}
Event resultingEvent = new WorkEffortReviewUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
