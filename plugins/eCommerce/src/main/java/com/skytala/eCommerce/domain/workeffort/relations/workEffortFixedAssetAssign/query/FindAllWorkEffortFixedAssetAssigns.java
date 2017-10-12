
package com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.event.WorkEffortFixedAssetAssignFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.mapper.WorkEffortFixedAssetAssignMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortFixedAssetAssign.model.WorkEffortFixedAssetAssign;


public class FindAllWorkEffortFixedAssetAssigns extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortFixedAssetAssign> returnVal = new ArrayList<WorkEffortFixedAssetAssign>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortFixedAssetAssign", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortFixedAssetAssignMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortFixedAssetAssignFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
