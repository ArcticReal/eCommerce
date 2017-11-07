package com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.event.TarpittedLoginViewUpdated;
import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.model.TarpittedLoginView;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateTarpittedLoginView extends Command {

private TarpittedLoginView elementToBeUpdated;

public UpdateTarpittedLoginView(TarpittedLoginView elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public TarpittedLoginView getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(TarpittedLoginView elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("TarpittedLoginView", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(TarpittedLoginView.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(TarpittedLoginView.class);
}
success = false;
}
Event resultingEvent = new TarpittedLoginViewUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
