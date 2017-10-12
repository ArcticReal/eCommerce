package com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.event.ShipmentReceiptAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.mapper.ShipmentReceiptMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.model.ShipmentReceipt;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentReceipt extends Command {

private ShipmentReceipt elementToBeAdded;
public AddShipmentReceipt(ShipmentReceipt elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentReceipt addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReceiptId(delegator.getNextSeqId("ShipmentReceipt"));
GenericValue newValue = delegator.makeValue("ShipmentReceipt", elementToBeAdded.mapAttributeField());
addedElement = ShipmentReceiptMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentReceiptAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
