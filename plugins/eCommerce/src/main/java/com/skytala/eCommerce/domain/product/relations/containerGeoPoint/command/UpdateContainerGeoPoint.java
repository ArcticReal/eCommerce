package com.skytala.eCommerce.domain.product.relations.containerGeoPoint.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.containerGeoPoint.event.ContainerGeoPointUpdated;
import com.skytala.eCommerce.domain.product.relations.containerGeoPoint.model.ContainerGeoPoint;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateContainerGeoPoint extends Command {

private ContainerGeoPoint elementToBeUpdated;

public UpdateContainerGeoPoint(ContainerGeoPoint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ContainerGeoPoint getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ContainerGeoPoint elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ContainerGeoPoint", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ContainerGeoPoint.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ContainerGeoPoint.class);
}
success = false;
}
Event resultingEvent = new ContainerGeoPointUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
