package com.skytala.eCommerce.domain.shipment.relations.shipment.command.methodType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.methodType.ShipmentMethodTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.methodType.ShipmentMethodTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.methodType.ShipmentMethodType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentMethodType extends Command {

private ShipmentMethodType elementToBeAdded;
public AddShipmentMethodType(ShipmentMethodType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentMethodType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentMethodTypeId(delegator.getNextSeqId("ShipmentMethodType"));
GenericValue newValue = delegator.makeValue("ShipmentMethodType", elementToBeAdded.mapAttributeField());
addedElement = ShipmentMethodTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentMethodTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
