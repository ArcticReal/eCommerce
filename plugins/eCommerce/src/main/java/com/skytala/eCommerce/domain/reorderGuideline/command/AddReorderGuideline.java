package com.skytala.eCommerce.domain.reorderGuideline.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.reorderGuideline.event.ReorderGuidelineAdded;
import com.skytala.eCommerce.domain.reorderGuideline.mapper.ReorderGuidelineMapper;
import com.skytala.eCommerce.domain.reorderGuideline.model.ReorderGuideline;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReorderGuideline extends Command {

private ReorderGuideline elementToBeAdded;
public AddReorderGuideline(ReorderGuideline elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReorderGuideline addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReorderGuidelineId(delegator.getNextSeqId("ReorderGuideline"));
GenericValue newValue = delegator.makeValue("ReorderGuideline", elementToBeAdded.mapAttributeField());
addedElement = ReorderGuidelineMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReorderGuidelineAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
