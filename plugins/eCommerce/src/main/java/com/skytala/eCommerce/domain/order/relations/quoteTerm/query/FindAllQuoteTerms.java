
package com.skytala.eCommerce.domain.order.relations.quoteTerm.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quoteTerm.event.QuoteTermFound;
import com.skytala.eCommerce.domain.order.relations.quoteTerm.mapper.QuoteTermMapper;
import com.skytala.eCommerce.domain.order.relations.quoteTerm.model.QuoteTerm;


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
