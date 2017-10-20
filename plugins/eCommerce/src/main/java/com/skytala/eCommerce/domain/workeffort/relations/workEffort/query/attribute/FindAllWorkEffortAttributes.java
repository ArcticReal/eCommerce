
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.attribute.WorkEffortAttributeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.attribute.WorkEffortAttributeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.attribute.WorkEffortAttribute;


public class FindAllWorkEffortAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortAttribute> returnVal = new ArrayList<WorkEffortAttribute>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
