
package com.skytala.eCommerce.domain.order.relations.quote.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quote.event.attribute.QuoteAttributeFound;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.attribute.QuoteAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.attribute.QuoteAttribute;


public class FindAllQuoteAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteAttribute> returnVal = new ArrayList<QuoteAttribute>();
try{
List<GenericValue> results = delegator.findAll("QuoteAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
