package com.skytala.eCommerce.domain.jobRequisition.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.jobRequisition.event.JobRequisitionAdded;
import com.skytala.eCommerce.domain.jobRequisition.mapper.JobRequisitionMapper;
import com.skytala.eCommerce.domain.jobRequisition.model.JobRequisition;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddJobRequisition extends Command {

private JobRequisition elementToBeAdded;
public AddJobRequisition(JobRequisition elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

JobRequisition addedElement = null;
boolean success = false;
try {
elementToBeAdded.setJobRequisitionId(delegator.getNextSeqId("JobRequisition"));
GenericValue newValue = delegator.makeValue("JobRequisition", elementToBeAdded.mapAttributeField());
addedElement = JobRequisitionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new JobRequisitionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
