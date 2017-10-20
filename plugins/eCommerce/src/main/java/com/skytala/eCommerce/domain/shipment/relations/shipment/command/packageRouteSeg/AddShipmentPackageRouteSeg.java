package com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageRouteSeg;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg.ShipmentPackageRouteSegAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.packageRouteSeg.ShipmentPackageRouteSegMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.packageRouteSeg.ShipmentPackageRouteSeg;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentPackageRouteSeg extends Command {

private ShipmentPackageRouteSeg elementToBeAdded;
public AddShipmentPackageRouteSeg(ShipmentPackageRouteSeg elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentPackageRouteSeg addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentPackageRouteSeg", elementToBeAdded.mapAttributeField());
addedElement = ShipmentPackageRouteSegMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentPackageRouteSegAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
