package com.skytala.eCommerce.domain.humanres.relations.perfReview.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.PerfReviewAdded;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.PerfReviewMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.PerfReview;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPerfReview extends Command {

private PerfReview elementToBeAdded;
public AddPerfReview(PerfReview elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PerfReview addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPerfReviewId(delegator.getNextSeqId("PerfReview"));
GenericValue newValue = delegator.makeValue("PerfReview", elementToBeAdded.mapAttributeField());
addedElement = PerfReviewMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PerfReviewAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
