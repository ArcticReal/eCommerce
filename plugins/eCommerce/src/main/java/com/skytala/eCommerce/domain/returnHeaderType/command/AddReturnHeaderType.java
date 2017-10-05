package com.skytala.eCommerce.domain.returnHeaderType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.returnHeaderType.event.ReturnHeaderTypeAdded;
import com.skytala.eCommerce.domain.returnHeaderType.mapper.ReturnHeaderTypeMapper;
import com.skytala.eCommerce.domain.returnHeaderType.model.ReturnHeaderType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnHeaderType extends Command {

private ReturnHeaderType elementToBeAdded;
public AddReturnHeaderType(ReturnHeaderType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnHeaderType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnHeaderTypeId(delegator.getNextSeqId("ReturnHeaderType"));
GenericValue newValue = delegator.makeValue("ReturnHeaderType", elementToBeAdded.mapAttributeField());
addedElement = ReturnHeaderTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnHeaderTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
