package com.skytala.eCommerce.domain.humanres.relations.perfReview.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.PerfReviewUpdated;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.PerfReview;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePerfReview extends Command {

private PerfReview elementToBeUpdated;

public UpdatePerfReview(PerfReview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PerfReview getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PerfReview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PerfReview", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PerfReview.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PerfReview.class);
}
success = false;
}
Event resultingEvent = new PerfReviewUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
