package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.AcctgTransUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.AcctgTrans;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAcctgTrans extends Command {

private AcctgTrans elementToBeUpdated;

public UpdateAcctgTrans(AcctgTrans elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AcctgTrans getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AcctgTrans elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AcctgTrans", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AcctgTrans.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AcctgTrans.class);
}
success = false;
}
Event resultingEvent = new AcctgTransUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
