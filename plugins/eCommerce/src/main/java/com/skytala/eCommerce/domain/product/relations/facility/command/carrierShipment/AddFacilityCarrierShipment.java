package com.skytala.eCommerce.domain.product.relations.facility.command.carrierShipment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.event.carrierShipment.FacilityCarrierShipmentAdded;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.carrierShipment.FacilityCarrierShipmentMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.carrierShipment.FacilityCarrierShipment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityCarrierShipment extends Command {

private FacilityCarrierShipment elementToBeAdded;
public AddFacilityCarrierShipment(FacilityCarrierShipment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityCarrierShipment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("FacilityCarrierShipment"));
GenericValue newValue = delegator.makeValue("FacilityCarrierShipment", elementToBeAdded.mapAttributeField());
addedElement = FacilityCarrierShipmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityCarrierShipmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
