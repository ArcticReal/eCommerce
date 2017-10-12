package com.skytala.eCommerce.domain.accounting.relations.acctgTransEntryType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntryType.event.AcctgTransEntryTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntryType.model.AcctgTransEntryType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAcctgTransEntryType extends Command {

private AcctgTransEntryType elementToBeUpdated;

public UpdateAcctgTransEntryType(AcctgTransEntryType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AcctgTransEntryType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AcctgTransEntryType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AcctgTransEntryType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AcctgTransEntryType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AcctgTransEntryType.class);
}
success = false;
}
Event resultingEvent = new AcctgTransEntryTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
