package com.skytala.eCommerce.domain.order.relations.custRequest.command.itemNote;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemNote.CustRequestItemNoteUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemNote.CustRequestItemNote;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCustRequestItemNote extends Command {

private CustRequestItemNote elementToBeUpdated;

public UpdateCustRequestItemNote(CustRequestItemNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CustRequestItemNote getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CustRequestItemNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CustRequestItemNote", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CustRequestItemNote.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CustRequestItemNote.class);
}
success = false;
}
Event resultingEvent = new CustRequestItemNoteUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
