
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.fixedAssetStd;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.fixedAssetStd.WorkEffortFixedAssetStdMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetStd.WorkEffortFixedAssetStd;


public class FindAllWorkEffortFixedAssetStds extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortFixedAssetStd> returnVal = new ArrayList<WorkEffortFixedAssetStd>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortFixedAssetStd", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortFixedAssetStdMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortFixedAssetStdFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
