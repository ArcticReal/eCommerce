package com.skytala.eCommerce.domain.shipment.relations.shipment.command.receiptRole;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.receiptRole.ShipmentReceiptRoleAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.receiptRole.ShipmentReceiptRoleMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receiptRole.ShipmentReceiptRole;
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
