package com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.event.AcctgTransAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransAttribute.model.AcctgTransAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAcctgTransAttribute extends Command {

private AcctgTransAttribute elementToBeUpdated;

public UpdateAcctgTransAttribute(AcctgTransAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AcctgTransAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AcctgTransAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AcctgTransAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AcctgTransAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AcctgTransAttribute.class);
}
success = false;
}
Event resultingEvent = new AcctgTransAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
