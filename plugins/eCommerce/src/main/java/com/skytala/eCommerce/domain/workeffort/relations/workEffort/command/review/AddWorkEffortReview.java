package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.review;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.review.WorkEffortReviewAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.review.WorkEffortReviewMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.review.WorkEffortReview;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortReview extends Command {

private WorkEffortReview elementToBeAdded;
public AddWorkEffortReview(WorkEffortReview elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortReview addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortReview", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortReviewMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortReviewAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
