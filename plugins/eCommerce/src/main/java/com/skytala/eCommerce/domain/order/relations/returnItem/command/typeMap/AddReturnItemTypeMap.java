package com.skytala.eCommerce.domain.order.relations.returnItem.command.typeMap;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.typeMap.ReturnItemTypeMapAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.typeMap.ReturnItemTypeMapMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.typeMap.ReturnItemTypeMap;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnItemTypeMap extends Command {

private ReturnItemTypeMap elementToBeAdded;
public AddReturnItemTypeMap(ReturnItemTypeMap elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnItemTypeMap addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnItemMapKey(delegator.getNextSeqId("ReturnItemTypeMap"));
GenericValue newValue = delegator.makeValue("ReturnItemTypeMap", elementToBeAdded.mapAttributeField());
addedElement = ReturnItemTypeMapMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnItemTypeMapAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
