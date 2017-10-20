package com.skytala.eCommerce.domain.shipment.relations.shipment.command.routeSegment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.routeSegment.ShipmentRouteSegmentAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.routeSegment.ShipmentRouteSegmentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.routeSegment.ShipmentRouteSegment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentRouteSegment extends Command {

private ShipmentRouteSegment elementToBeAdded;
public AddShipmentRouteSegment(ShipmentRouteSegment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentRouteSegment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentRouteSegmentId(delegator.getNextSeqId("ShipmentRouteSegment"));
GenericValue newValue = delegator.makeValue("ShipmentRouteSegment", elementToBeAdded.mapAttributeField());
addedElement = ShipmentRouteSegmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentRouteSegmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
