package com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.event.WorkEffortContactMechAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.mapper.WorkEffortContactMechMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContactMech.model.WorkEffortContactMech;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortContactMech extends Command {

private WorkEffortContactMech elementToBeAdded;
public AddWorkEffortContactMech(WorkEffortContactMech elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortContactMech addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortContactMech", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortContactMechMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortContactMechAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
