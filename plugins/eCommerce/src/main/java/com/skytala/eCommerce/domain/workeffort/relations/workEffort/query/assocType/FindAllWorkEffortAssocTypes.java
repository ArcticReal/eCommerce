
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.assocType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocType.WorkEffortAssocTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocType.WorkEffortAssocTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocType.WorkEffortAssocType;


public class FindAllWorkEffortAssocTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortAssocType> returnVal = new ArrayList<WorkEffortAssocType>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortAssocType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortAssocTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortAssocTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
