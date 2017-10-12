package com.skytala.eCommerce.domain.accounting.relations.finAccountTrans.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTrans.event.FinAccountTransUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTrans.model.FinAccountTrans;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFinAccountTrans extends Command {

private FinAccountTrans elementToBeUpdated;

public UpdateFinAccountTrans(FinAccountTrans elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FinAccountTrans getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FinAccountTrans elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FinAccountTrans", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FinAccountTrans.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FinAccountTrans.class);
}
success = false;
}
Event resultingEvent = new FinAccountTransUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
