package com.skytala.eCommerce.domain.humanres.relations.jobRequisition.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.event.JobRequisitionUpdated;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.model.JobRequisition;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateJobRequisition extends Command {

private JobRequisition elementToBeUpdated;

public UpdateJobRequisition(JobRequisition elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public JobRequisition getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(JobRequisition elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("JobRequisition", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(JobRequisition.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(JobRequisition.class);
}
success = false;
}
Event resultingEvent = new JobRequisitionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
