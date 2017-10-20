
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.purposeType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.purposeType.WorkEffortPurposeTypeFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.purposeType.WorkEffortPurposeTypeMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.purposeType.WorkEffortPurposeType;


public class FindAllWorkEffortPurposeTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortPurposeType> returnVal = new ArrayList<WorkEffortPurposeType>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortPurposeType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortPurposeTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortPurposeTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
