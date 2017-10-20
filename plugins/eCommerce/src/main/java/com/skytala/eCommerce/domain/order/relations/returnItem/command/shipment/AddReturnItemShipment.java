package com.skytala.eCommerce.domain.order.relations.returnItem.command.shipment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.shipment.ReturnItemShipmentAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.shipment.ReturnItemShipmentMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.shipment.ReturnItemShipment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnItemShipment extends Command {

private ReturnItemShipment elementToBeAdded;
public AddReturnItemShipment(ReturnItemShipment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnItemShipment addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ReturnItemShipment", elementToBeAdded.mapAttributeField());
addedElement = ReturnItemShipmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnItemShipmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
