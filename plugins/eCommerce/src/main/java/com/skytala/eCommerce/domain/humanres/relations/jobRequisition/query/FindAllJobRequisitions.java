
package com.skytala.eCommerce.domain.humanres.relations.jobRequisition.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.event.JobRequisitionFound;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.mapper.JobRequisitionMapper;
import com.skytala.eCommerce.domain.humanres.relations.jobRequisition.model.JobRequisition;


public class FindAllJobRequisitions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<JobRequisition> returnVal = new ArrayList<JobRequisition>();
try{
List<GenericValue> results = delegator.findAll("JobRequisition", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(JobRequisitionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new JobRequisitionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
