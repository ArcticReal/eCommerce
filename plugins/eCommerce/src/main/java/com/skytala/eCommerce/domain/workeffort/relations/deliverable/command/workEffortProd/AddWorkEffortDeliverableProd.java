package com.skytala.eCommerce.domain.workeffort.relations.deliverable.command.workEffortProd;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.event.workEffortProd.WorkEffortDeliverableProdAdded;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.mapper.workEffortProd.WorkEffortDeliverableProdMapper;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.workEffortProd.WorkEffortDeliverableProd;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortDeliverableProd extends Command {

private WorkEffortDeliverableProd elementToBeAdded;
public AddWorkEffortDeliverableProd(WorkEffortDeliverableProd elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortDeliverableProd addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortDeliverableProd", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortDeliverableProdMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortDeliverableProdAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
