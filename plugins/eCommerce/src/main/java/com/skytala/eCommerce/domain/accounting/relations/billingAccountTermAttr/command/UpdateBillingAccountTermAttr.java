package com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.event.BillingAccountTermAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.model.BillingAccountTermAttr;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBillingAccountTermAttr extends Command {

private BillingAccountTermAttr elementToBeUpdated;

public UpdateBillingAccountTermAttr(BillingAccountTermAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BillingAccountTermAttr getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BillingAccountTermAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BillingAccountTermAttr", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BillingAccountTermAttr.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BillingAccountTermAttr.class);
}
success = false;
}
Event resultingEvent = new BillingAccountTermAttrUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
