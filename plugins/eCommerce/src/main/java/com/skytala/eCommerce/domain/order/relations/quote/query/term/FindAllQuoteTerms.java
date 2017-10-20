
package com.skytala.eCommerce.domain.order.relations.quote.query.term;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quote.event.term.QuoteTermFound;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.term.QuoteTermMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.term.QuoteTerm;


public class FindAllQuoteTerms extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteTerm> returnVal = new ArrayList<QuoteTerm>();
try{
List<GenericValue> results = delegator.findAll("QuoteTerm", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteTermMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteTermFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
