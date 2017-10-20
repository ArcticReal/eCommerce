package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entry;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entry.AcctgTransEntryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entry.AcctgTransEntry;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAcctgTransEntry extends Command {

private AcctgTransEntry elementToBeUpdated;

public UpdateAcctgTransEntry(AcctgTransEntry elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AcctgTransEntry getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AcctgTransEntry elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AcctgTransEntry", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AcctgTransEntry.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AcctgTransEntry.class);
}
success = false;
}
Event resultingEvent = new AcctgTransEntryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
