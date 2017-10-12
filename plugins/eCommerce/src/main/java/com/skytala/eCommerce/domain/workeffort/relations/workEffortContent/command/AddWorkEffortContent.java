package com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.event.WorkEffortContentAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.mapper.WorkEffortContentMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortContent.model.WorkEffortContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortContent extends Command {

private WorkEffortContent elementToBeAdded;
public AddWorkEffortContent(WorkEffortContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortContent", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
