package com.skytala.eCommerce.domain.shipmentCostEstimate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipmentCostEstimate.event.ShipmentCostEstimateAdded;
import com.skytala.eCommerce.domain.shipmentCostEstimate.mapper.ShipmentCostEstimateMapper;
import com.skytala.eCommerce.domain.shipmentCostEstimate.model.ShipmentCostEstimate;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentCostEstimate extends Command {

private ShipmentCostEstimate elementToBeAdded;
public AddShipmentCostEstimate(ShipmentCostEstimate elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentCostEstimate addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentCostEstimateId(delegator.getNextSeqId("ShipmentCostEstimate"));
GenericValue newValue = delegator.makeValue("ShipmentCostEstimate", elementToBeAdded.mapAttributeField());
addedElement = ShipmentCostEstimateMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentCostEstimateAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
