package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.status;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.status.WorkEffortStatusAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.status.WorkEffortStatusMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.status.WorkEffortStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortStatus extends Command {

private WorkEffortStatus elementToBeAdded;
public AddWorkEffortStatus(WorkEffortStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortStatus addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortStatus", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
