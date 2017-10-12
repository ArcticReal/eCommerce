
package com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.event.WorkEffortSearchResultFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.mapper.WorkEffortSearchResultMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortSearchResult.model.WorkEffortSearchResult;


public class FindAllWorkEffortSearchResults extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortSearchResult> returnVal = new ArrayList<WorkEffortSearchResult>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortSearchResult", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortSearchResultMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortSearchResultFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
