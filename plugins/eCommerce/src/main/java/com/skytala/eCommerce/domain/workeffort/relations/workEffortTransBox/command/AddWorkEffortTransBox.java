package com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.event.WorkEffortTransBoxAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.mapper.WorkEffortTransBoxMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortTransBox.model.WorkEffortTransBox;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortTransBox extends Command {

private WorkEffortTransBox elementToBeAdded;
public AddWorkEffortTransBox(WorkEffortTransBox elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortTransBox addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortTransBox", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortTransBoxMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortTransBoxAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
