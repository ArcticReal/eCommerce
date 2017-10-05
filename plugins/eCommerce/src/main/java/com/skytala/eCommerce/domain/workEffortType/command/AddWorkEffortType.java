package com.skytala.eCommerce.domain.workEffortType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workEffortType.event.WorkEffortTypeAdded;
import com.skytala.eCommerce.domain.workEffortType.mapper.WorkEffortTypeMapper;
import com.skytala.eCommerce.domain.workEffortType.model.WorkEffortType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortType extends Command {

private WorkEffortType elementToBeAdded;
public AddWorkEffortType(WorkEffortType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWorkEffortTypeId(delegator.getNextSeqId("WorkEffortType"));
GenericValue newValue = delegator.makeValue("WorkEffortType", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
