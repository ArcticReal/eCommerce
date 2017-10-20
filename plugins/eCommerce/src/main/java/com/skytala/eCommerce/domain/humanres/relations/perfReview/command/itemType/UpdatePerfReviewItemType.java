package com.skytala.eCommerce.domain.humanres.relations.perfReview.command.itemType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.itemType.PerfReviewItemTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.itemType.PerfReviewItemType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePerfReviewItemType extends Command {

private PerfReviewItemType elementToBeUpdated;

public UpdatePerfReviewItemType(PerfReviewItemType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PerfReviewItemType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PerfReviewItemType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PerfReviewItemType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PerfReviewItemType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PerfReviewItemType.class);
}
success = false;
}
Event resultingEvent = new PerfReviewItemTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
