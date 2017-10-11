package com.skytala.eCommerce.domain.order.relations.returnType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnType.event.ReturnTypeAdded;
import com.skytala.eCommerce.domain.order.relations.returnType.mapper.ReturnTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnType.model.ReturnType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnType extends Command {

private ReturnType elementToBeAdded;
public AddReturnType(ReturnType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnTypeId(delegator.getNextSeqId("ReturnType"));
GenericValue newValue = delegator.makeValue("ReturnType", elementToBeAdded.mapAttributeField());
addedElement = ReturnTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
