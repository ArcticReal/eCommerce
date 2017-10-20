
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.searchResult;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.searchResult.WorkEffortSearchResultFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.searchResult.WorkEffortSearchResultMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.searchResult.WorkEffortSearchResult;


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
