package com.skytala.eCommerce.domain.product.relations.facilityContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facilityContent.event.FacilityContentUpdated;
import com.skytala.eCommerce.domain.product.relations.facilityContent.model.FacilityContent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFacilityContent extends Command {

private FacilityContent elementToBeUpdated;

public UpdateFacilityContent(FacilityContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FacilityContent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FacilityContent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FacilityContent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FacilityContent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FacilityContent.class);
}
success = false;
}
Event resultingEvent = new FacilityContentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
