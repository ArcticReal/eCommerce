package com.skytala.eCommerce.domain.order.relations.returnAdjustment.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.type.ReturnAdjustmentTypeAdded;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.mapper.type.ReturnAdjustmentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.type.ReturnAdjustmentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnAdjustmentType extends Command {

private ReturnAdjustmentType elementToBeAdded;
public AddReturnAdjustmentType(ReturnAdjustmentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnAdjustmentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnAdjustmentTypeId(delegator.getNextSeqId("ReturnAdjustmentType"));
GenericValue newValue = delegator.makeValue("ReturnAdjustmentType", elementToBeAdded.mapAttributeField());
addedElement = ReturnAdjustmentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnAdjustmentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
