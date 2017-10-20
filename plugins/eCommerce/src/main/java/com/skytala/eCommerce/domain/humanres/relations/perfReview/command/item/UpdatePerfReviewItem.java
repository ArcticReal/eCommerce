package com.skytala.eCommerce.domain.humanres.relations.perfReview.command.item;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemUpdated;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.item.PerfReviewItem;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePerfReviewItem extends Command {

private PerfReviewItem elementToBeUpdated;

public UpdatePerfReviewItem(PerfReviewItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PerfReviewItem getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PerfReviewItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PerfReviewItem", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PerfReviewItem.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PerfReviewItem.class);
}
success = false;
}
Event resultingEvent = new PerfReviewItemUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
