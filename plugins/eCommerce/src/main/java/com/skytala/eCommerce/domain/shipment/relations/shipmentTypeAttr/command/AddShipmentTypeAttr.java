package com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.event.ShipmentTypeAttrAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.mapper.ShipmentTypeAttrMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentTypeAttr.model.ShipmentTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentTypeAttr extends Command {

private ShipmentTypeAttr elementToBeAdded;
public AddShipmentTypeAttr(ShipmentTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = ShipmentTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
