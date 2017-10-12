package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.WorkEffortAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.WorkEffortMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.WorkEffort;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffort extends Command {

private WorkEffort elementToBeAdded;
public AddWorkEffort(WorkEffort elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffort addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWorkEffortId(delegator.getNextSeqId("WorkEffort"));
GenericValue newValue = delegator.makeValue("WorkEffort", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
