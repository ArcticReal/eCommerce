package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.attribute.WorkEffortAttributeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.attribute.WorkEffortAttributeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.attribute.WorkEffortAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortAttribute extends Command {

private WorkEffortAttribute elementToBeAdded;
public AddWorkEffortAttribute(WorkEffortAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortAttribute", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
