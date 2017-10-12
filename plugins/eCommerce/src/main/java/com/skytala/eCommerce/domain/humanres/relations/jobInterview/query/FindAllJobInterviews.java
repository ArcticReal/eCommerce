
package com.skytala.eCommerce.domain.humanres.relations.jobInterview.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.event.JobInterviewFound;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.mapper.JobInterviewMapper;
import com.skytala.eCommerce.domain.humanres.relations.jobInterview.model.JobInterview;


public class FindAllJobInterviews extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<JobInterview> returnVal = new ArrayList<JobInterview>();
try{
List<GenericValue> results = delegator.findAll("JobInterview", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(JobInterviewMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new JobInterviewFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
