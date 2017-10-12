package com.skytala.eCommerce.domain.humanres.relations.jobInterview.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.JobInterviewUpdated;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.JobInterview;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateJobInterview extends Command {

private JobInterview elementToBeUpdated;

public UpdateJobInterview(JobInterview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public JobInterview getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(JobInterview elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("JobInterview", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(JobInterview.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(JobInterview.class);
}
success = false;
}
Event resultingEvent = new JobInterviewUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
