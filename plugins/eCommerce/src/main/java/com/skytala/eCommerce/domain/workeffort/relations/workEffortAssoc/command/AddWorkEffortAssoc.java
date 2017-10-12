package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.event.WorkEffortAssocAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.mapper.WorkEffortAssocMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.model.WorkEffortAssoc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortAssoc extends Command {

private WorkEffortAssoc elementToBeAdded;
public AddWorkEffortAssoc(WorkEffortAssoc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortAssoc addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortAssoc", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortAssocMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortAssocAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
