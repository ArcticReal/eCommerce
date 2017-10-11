package com.skytala.eCommerce.domain.order.relations.returnHeader.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnHeader.event.ReturnHeaderAdded;
import com.skytala.eCommerce.domain.order.relations.returnHeader.mapper.ReturnHeaderMapper;
import com.skytala.eCommerce.domain.order.relations.returnHeader.model.ReturnHeader;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnHeader extends Command {

private ReturnHeader elementToBeAdded;
public AddReturnHeader(ReturnHeader elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnHeader addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnId(delegator.getNextSeqId("ReturnHeader"));
GenericValue newValue = delegator.makeValue("ReturnHeader", elementToBeAdded.mapAttributeField());
addedElement = ReturnHeaderMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnHeaderAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
