
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.assocAttribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.assocAttribute.WorkEffortAssocAttributeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.assocAttribute.WorkEffortAssocAttributeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.assocAttribute.WorkEffortAssocAttribute;


public class FindAllWorkEffortAssocAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortAssocAttribute> returnVal = new ArrayList<WorkEffortAssocAttribute>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortAssocAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortAssocAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortAssocAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
