package com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.event.ShipmentPackageContentAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.mapper.ShipmentPackageContentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackageContent.model.ShipmentPackageContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentPackageContent extends Command {

private ShipmentPackageContent elementToBeAdded;
public AddShipmentPackageContent(ShipmentPackageContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentPackageContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentPackageContent", elementToBeAdded.mapAttributeField());
addedElement = ShipmentPackageContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentPackageContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
