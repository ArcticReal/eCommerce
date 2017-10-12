package com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.event.WorkEffortGoodStandardAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.mapper.WorkEffortGoodStandardMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortGoodStandard.model.WorkEffortGoodStandard;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortGoodStandard extends Command {

private WorkEffortGoodStandard elementToBeAdded;
public AddWorkEffortGoodStandard(WorkEffortGoodStandard elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortGoodStandard addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortGoodStandard", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortGoodStandardMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortGoodStandardAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
