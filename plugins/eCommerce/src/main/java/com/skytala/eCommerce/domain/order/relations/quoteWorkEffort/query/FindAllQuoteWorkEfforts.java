
package com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.event.QuoteWorkEffortFound;
import com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.mapper.QuoteWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.model.QuoteWorkEffort;


public class FindAllQuoteWorkEfforts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteWorkEffort> returnVal = new ArrayList<QuoteWorkEffort>();
try{
List<GenericValue> results = delegator.findAll("QuoteWorkEffort", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteWorkEffortMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteWorkEffortFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
