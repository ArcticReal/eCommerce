package com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.event.FacilityLocationGeoPointUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityLocationGeoPoint.model.FacilityLocationGeoPoint;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityLocationGeoPoint extends Command {

private FacilityLocationGeoPoint elementToBeUpdated;

public UpdateFacilityLocationGeoPoint(FacilityLocationGeoPoint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityLocationGeoPoint getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityLocationGeoPoint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityLocationGeoPoint", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityLocationGeoPoint.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityLocationGeoPoint.class);
}
success = false;
}
Event resultingEvent = new FacilityLocationGeoPointUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
