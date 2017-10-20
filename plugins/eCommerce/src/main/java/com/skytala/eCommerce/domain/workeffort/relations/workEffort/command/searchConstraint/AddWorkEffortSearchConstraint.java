package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.searchConstraint;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchConstraint.WorkEffortSearchConstraintAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.searchConstraint.WorkEffortSearchConstraintMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchConstraint.WorkEffortSearchConstraint;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortSearchConstraint extends Command {

private WorkEffortSearchConstraint elementToBeAdded;
public AddWorkEffortSearchConstraint(WorkEffortSearchConstraint elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortSearchConstraint addedElement = null;
boolean success = false;
try {
elementToBeAdded.setConstraintSeqId(delegator.getNextSeqId("WorkEffortSearchConstraint"));
GenericValue newValue = delegator.makeValue("WorkEffortSearchConstraint", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortSearchConstraintMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortSearchConstraintAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
