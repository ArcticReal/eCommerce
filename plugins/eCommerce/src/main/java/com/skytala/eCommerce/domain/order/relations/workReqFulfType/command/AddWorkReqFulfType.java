package com.skytala.eCommerce.domain.order.relations.workReqFulfType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.event.WorkReqFulfTypeAdded;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.mapper.WorkReqFulfTypeMapper;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.model.WorkReqFulfType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkReqFulfType extends Command {

private WorkReqFulfType elementToBeAdded;
public AddWorkReqFulfType(WorkReqFulfType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkReqFulfType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWorkReqFulfTypeId(delegator.getNextSeqId("WorkReqFulfType"));
GenericValue newValue = delegator.makeValue("WorkReqFulfType", elementToBeAdded.mapAttributeField());
addedElement = WorkReqFulfTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkReqFulfTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
