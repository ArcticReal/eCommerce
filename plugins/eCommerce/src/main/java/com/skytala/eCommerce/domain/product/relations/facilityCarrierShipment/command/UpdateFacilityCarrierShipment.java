package com.skytala.eCommerce.domain.product.relations.facilityCarrierShipment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facilityCarrierShipment.event.FacilityCarrierShipmentUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityCarrierShipment.model.FacilityCarrierShipment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityCarrierShipment extends Command {

private FacilityCarrierShipment elementToBeUpdated;

public UpdateFacilityCarrierShipment(FacilityCarrierShipment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityCarrierShipment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityCarrierShipment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityCarrierShipment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityCarrierShipment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityCarrierShipment.class);
}
success = false;
}
Event resultingEvent = new FacilityCarrierShipmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
