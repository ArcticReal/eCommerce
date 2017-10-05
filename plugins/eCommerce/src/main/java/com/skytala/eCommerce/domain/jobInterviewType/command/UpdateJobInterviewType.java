package com.skytala.eCommerce.domain.jobInterviewType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.jobInterviewType.event.JobInterviewTypeUpdated;
import com.skytala.eCommerce.domain.jobInterviewType.model.JobInterviewType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateJobInterviewType extends Command {

private JobInterviewType elementToBeUpdated;

public UpdateJobInterviewType(JobInterviewType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public JobInterviewType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(JobInterviewType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("JobInterviewType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(JobInterviewType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(JobInterviewType.class);
}
success = false;
}
Event resultingEvent = new JobInterviewTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
