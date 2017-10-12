package com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.event.ShipmentReceiptRoleAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.mapper.ShipmentReceiptRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceiptRole.model.ShipmentReceiptRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentReceiptRole extends Command {

private ShipmentReceiptRole elementToBeAdded;
public AddShipmentReceiptRole(ShipmentReceiptRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentReceiptRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("ShipmentReceiptRole"));
GenericValue newValue = delegator.makeValue("ShipmentReceiptRole", elementToBeAdded.mapAttributeField());
addedElement = ShipmentReceiptRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentReceiptRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
