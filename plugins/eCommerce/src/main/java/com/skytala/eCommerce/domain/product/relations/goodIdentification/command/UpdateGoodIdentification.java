package com.skytala.eCommerce.domain.product.relations.goodIdentification.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.GoodIdentificationUpdated;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.GoodIdentification;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGoodIdentification extends Command {

private GoodIdentification elementToBeUpdated;

public UpdateGoodIdentification(GoodIdentification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GoodIdentification getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GoodIdentification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GoodIdentification", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GoodIdentification.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GoodIdentification.class);
}
success = false;
}
Event resultingEvent = new GoodIdentificationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
