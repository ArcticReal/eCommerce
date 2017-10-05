package com.skytala.eCommerce.domain.returnItemResponse.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.returnItemResponse.event.ReturnItemResponseAdded;
import com.skytala.eCommerce.domain.returnItemResponse.mapper.ReturnItemResponseMapper;
import com.skytala.eCommerce.domain.returnItemResponse.model.ReturnItemResponse;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnItemResponse extends Command {

private ReturnItemResponse elementToBeAdded;
public AddReturnItemResponse(ReturnItemResponse elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnItemResponse addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnItemResponseId(delegator.getNextSeqId("ReturnItemResponse"));
GenericValue newValue = delegator.makeValue("ReturnItemResponse", elementToBeAdded.mapAttributeField());
addedElement = ReturnItemResponseMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnItemResponseAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
