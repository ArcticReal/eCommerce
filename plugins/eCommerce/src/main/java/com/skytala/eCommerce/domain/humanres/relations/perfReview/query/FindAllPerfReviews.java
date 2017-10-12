
package com.skytala.eCommerce.domain.humanres.relations.perfReview.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.event.PerfReviewFound;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.mapper.PerfReviewMapper;
import com.skytala.eCommerce.domain.humanres.relations.perfReview.model.PerfReview;


public class FindAllPerfReviews extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PerfReview> returnVal = new ArrayList<PerfReview>();
try{
List<GenericValue> results = delegator.findAll("PerfReview", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PerfReviewMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PerfReviewFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
