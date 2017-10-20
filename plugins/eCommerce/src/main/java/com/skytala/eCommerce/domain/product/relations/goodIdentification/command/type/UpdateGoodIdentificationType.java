package com.skytala.eCommerce.domain.product.relations.goodIdentification.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.event.type.GoodIdentificationTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.goodIdentification.model.type.GoodIdentificationType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGoodIdentificationType extends Command {

private GoodIdentificationType elementToBeUpdated;

public UpdateGoodIdentificationType(GoodIdentificationType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GoodIdentificationType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GoodIdentificationType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GoodIdentificationType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GoodIdentificationType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GoodIdentificationType.class);
}
success = false;
}
Event resultingEvent = new GoodIdentificationTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
