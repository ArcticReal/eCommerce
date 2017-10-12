package com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.event.ShipmentItemFeatureAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.mapper.ShipmentItemFeatureMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.model.ShipmentItemFeature;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentItemFeature extends Command {

private ShipmentItemFeature elementToBeAdded;
public AddShipmentItemFeature(ShipmentItemFeature elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentItemFeature addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentItemSeqId(delegator.getNextSeqId("ShipmentItemFeature"));
GenericValue newValue = delegator.makeValue("ShipmentItemFeature", elementToBeAdded.mapAttributeField());
addedElement = ShipmentItemFeatureMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentItemFeatureAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
