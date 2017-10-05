package com.skytala.eCommerce.domain.returnAdjustment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.returnAdjustment.event.ReturnAdjustmentAdded;
import com.skytala.eCommerce.domain.returnAdjustment.mapper.ReturnAdjustmentMapper;
import com.skytala.eCommerce.domain.returnAdjustment.model.ReturnAdjustment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnAdjustment extends Command {

private ReturnAdjustment elementToBeAdded;
public AddReturnAdjustment(ReturnAdjustment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnAdjustment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnAdjustmentId(delegator.getNextSeqId("ReturnAdjustment"));
GenericValue newValue = delegator.makeValue("ReturnAdjustment", elementToBeAdded.mapAttributeField());
addedElement = ReturnAdjustmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnAdjustmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
