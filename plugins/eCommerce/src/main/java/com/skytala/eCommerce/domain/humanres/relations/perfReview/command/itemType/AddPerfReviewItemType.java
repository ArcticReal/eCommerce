package com.skytala.eCommerce.domain.humanres.relations.perfReview.command.itemType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.itemType.PerfReviewItemTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.itemType.PerfReviewItemTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.itemType.PerfReviewItemType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPerfReviewItemType extends Command {

private PerfReviewItemType elementToBeAdded;
public AddPerfReviewItemType(PerfReviewItemType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PerfReviewItemType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPerfReviewItemTypeId(delegator.getNextSeqId("PerfReviewItemType"));
GenericValue newValue = delegator.makeValue("PerfReviewItemType", elementToBeAdded.mapAttributeField());
addedElement = PerfReviewItemTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PerfReviewItemTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
