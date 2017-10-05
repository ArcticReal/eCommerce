package com.skytala.eCommerce.domain.jobInterview.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.jobInterview.event.JobInterviewAdded;
import com.skytala.eCommerce.domain.jobInterview.mapper.JobInterviewMapper;
import com.skytala.eCommerce.domain.jobInterview.model.JobInterview;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddJobInterview extends Command {

private JobInterview elementToBeAdded;
public AddJobInterview(JobInterview elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

JobInterview addedElement = null;
boolean success = false;
try {
elementToBeAdded.setJobInterviewId(delegator.getNextSeqId("JobInterview"));
GenericValue newValue = delegator.makeValue("JobInterview", elementToBeAdded.mapAttributeField());
addedElement = JobInterviewMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new JobInterviewAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
