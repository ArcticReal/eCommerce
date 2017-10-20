package com.skytala.eCommerce.domain.order.relations.returnItem.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.type.ReturnItemTypeAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.type.ReturnItemTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.type.ReturnItemType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnItemType extends Command {

private ReturnItemType elementToBeAdded;
public AddReturnItemType(ReturnItemType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnItemType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnItemTypeId(delegator.getNextSeqId("ReturnItemType"));
GenericValue newValue = delegator.makeValue("ReturnItemType", elementToBeAdded.mapAttributeField());
addedElement = ReturnItemTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnItemTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
