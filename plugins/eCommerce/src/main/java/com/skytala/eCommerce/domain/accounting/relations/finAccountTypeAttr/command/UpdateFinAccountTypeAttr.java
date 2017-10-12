package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.event.FinAccountTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.model.FinAccountTypeAttr;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFinAccountTypeAttr extends Command {

private FinAccountTypeAttr elementToBeUpdated;

public UpdateFinAccountTypeAttr(FinAccountTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FinAccountTypeAttr getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FinAccountTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FinAccountTypeAttr", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FinAccountTypeAttr.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FinAccountTypeAttr.class);
}
success = false;
}
Event resultingEvent = new FinAccountTypeAttrUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
