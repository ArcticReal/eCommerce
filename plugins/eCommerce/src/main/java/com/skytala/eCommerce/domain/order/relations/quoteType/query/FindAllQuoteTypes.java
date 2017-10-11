
package com.skytala.eCommerce.domain.order.relations.quoteType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quoteType.event.QuoteTypeFound;
import com.skytala.eCommerce.domain.order.relations.quoteType.mapper.QuoteTypeMapper;
import com.skytala.eCommerce.domain.order.relations.quoteType.model.QuoteType;


public class FindAllQuoteTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteType> returnVal = new ArrayList<QuoteType>();
try{
List<GenericValue> results = delegator.findAll("QuoteType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
