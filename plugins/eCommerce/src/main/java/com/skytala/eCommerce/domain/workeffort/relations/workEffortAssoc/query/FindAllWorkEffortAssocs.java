
package com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.event.WorkEffortAssocFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.mapper.WorkEffortAssocMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortAssoc.model.WorkEffortAssoc;


public class FindAllWorkEffortAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortAssoc> returnVal = new ArrayList<WorkEffortAssoc>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
