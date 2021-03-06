package com.skytala.eCommerce.domain.humanres.relations.perfReview.command.item;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.item.PerfReviewItemAdded;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.item.PerfReviewItemMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.item.PerfReviewItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPerfReviewItem extends Command {

private PerfReviewItem elementToBeAdded;
public AddPerfReviewItem(PerfReviewItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PerfReviewItem addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PerfReviewItem", elementToBeAdded.mapAttributeField());
addedElement = PerfReviewItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PerfReviewItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
