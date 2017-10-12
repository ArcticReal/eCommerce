
package com.skytala.eCommerce.domain.humanres.relations.jobInterviewType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.jobInterviewType.event.JobInterviewTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.jobInterviewType.mapper.JobInterviewTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.jobInterviewType.model.JobInterviewType;


public class FindAllJobInterviewTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<JobInterviewType> returnVal = new ArrayList<JobInterviewType>();
try{
List<GenericValue> results = delegator.findAll("JobInterviewType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(JobInterviewTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new JobInterviewTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
