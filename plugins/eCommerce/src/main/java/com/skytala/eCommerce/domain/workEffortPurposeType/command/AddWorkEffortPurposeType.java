package com.skytala.eCommerce.domain.workEffortPurposeType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workEffortPurposeType.event.WorkEffortPurposeTypeAdded;
import com.skytala.eCommerce.domain.workEffortPurposeType.mapper.WorkEffortPurposeTypeMapper;
import com.skytala.eCommerce.domain.workEffortPurposeType.model.WorkEffortPurposeType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortPurposeType extends Command {

private WorkEffortPurposeType elementToBeAdded;
public AddWorkEffortPurposeType(WorkEffortPurposeType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortPurposeType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWorkEffortPurposeTypeId(delegator.getNextSeqId("WorkEffortPurposeType"));
GenericValue newValue = delegator.makeValue("WorkEffortPurposeType", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortPurposeTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortPurposeTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
