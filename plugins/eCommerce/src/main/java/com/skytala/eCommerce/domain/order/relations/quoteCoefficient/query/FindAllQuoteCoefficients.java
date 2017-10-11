
package com.skytala.eCommerce.domain.order.relations.quoteCoefficient.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quoteCoefficient.event.QuoteCoefficientFound;
import com.skytala.eCommerce.domain.order.relations.quoteCoefficient.mapper.QuoteCoefficientMapper;
import com.skytala.eCommerce.domain.order.relations.quoteCoefficient.model.QuoteCoefficient;


public class FindAllQuoteCoefficients extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteCoefficient> returnVal = new ArrayList<QuoteCoefficient>();
try{
List<GenericValue> results = delegator.findAll("QuoteCoefficient", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteCoefficientMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteCoefficientFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
