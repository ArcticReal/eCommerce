package com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.event.ShipmentPackageAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.mapper.ShipmentPackageMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.model.ShipmentPackage;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentPackage extends Command {

private ShipmentPackage elementToBeAdded;
public AddShipmentPackage(ShipmentPackage elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentPackage addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentPackageSeqId(delegator.getNextSeqId("ShipmentPackage"));
GenericValue newValue = delegator.makeValue("ShipmentPackage", elementToBeAdded.mapAttributeField());
addedElement = ShipmentPackageMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentPackageAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
