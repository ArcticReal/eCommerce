package com.skytala.eCommerce.domain.humanres.relations.jobInterview.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.type.JobInterviewTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.mapper.type.JobInterviewTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.type.JobInterviewType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddJobInterviewType extends Command {

private JobInterviewType elementToBeAdded;
public AddJobInterviewType(JobInterviewType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

JobInterviewType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setJobInterviewTypeId(delegator.getNextSeqId("JobInterviewType"));
GenericValue newValue = delegator.makeValue("JobInterviewType", elementToBeAdded.mapAttributeField());
addedElement = JobInterviewTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new JobInterviewTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
