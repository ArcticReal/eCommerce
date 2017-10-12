package com.skytala.eCommerce.domain.humanres.relations.employmentApp.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.employmentApp.event.EmploymentAppUpdated;
import com.skytala.eCommerce.domain.humanres.relations.employmentApp.model.EmploymentApp;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEmploymentApp extends Command {

private EmploymentApp elementToBeUpdated;

public UpdateEmploymentApp(EmploymentApp elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EmploymentApp getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EmploymentApp elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EmploymentApp", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EmploymentApp.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EmploymentApp.class);
}
success = false;
}
Event resultingEvent = new EmploymentAppUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
