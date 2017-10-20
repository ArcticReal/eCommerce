package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.goodStandardType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.goodStandardType.WorkEffortGoodStandardTypeAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.goodStandardType.WorkEffortGoodStandardTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.goodStandardType.WorkEffortGoodStandardType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortGoodStandardType extends Command {

private WorkEffortGoodStandardType elementToBeAdded;
public AddWorkEffortGoodStandardType(WorkEffortGoodStandardType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortGoodStandardType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWorkEffortGoodStdTypeId(delegator.getNextSeqId("WorkEffortGoodStandardType"));
GenericValue newValue = delegator.makeValue("WorkEffortGoodStandardType", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortGoodStandardTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortGoodStandardTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
