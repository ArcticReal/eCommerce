package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.type.AcctgTransTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.type.AcctgTransType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateAcctgTransType extends Command {

private AcctgTransType elementToBeUpdated;

public UpdateAcctgTransType(AcctgTransType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public AcctgTransType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(AcctgTransType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("AcctgTransType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(AcctgTransType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(AcctgTransType.class);
}
success = false;
}
Event resultingEvent = new AcctgTransTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
