
package com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.event.WorkEffortReviewFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.mapper.WorkEffortReviewMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortReview.model.WorkEffortReview;


public class FindAllWorkEffortReviews extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortReview> returnVal = new ArrayList<WorkEffortReview>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortReview", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortReviewMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortReviewFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
