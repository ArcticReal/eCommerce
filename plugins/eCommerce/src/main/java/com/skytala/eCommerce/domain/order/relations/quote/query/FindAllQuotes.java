
package com.skytala.eCommerce.domain.order.relations.quote.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quote.event.QuoteFound;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.QuoteMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.Quote;


public class FindAllQuotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Quote> returnVal = new ArrayList<Quote>();
try{
List<GenericValue> results = delegator.findAll("Quote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
