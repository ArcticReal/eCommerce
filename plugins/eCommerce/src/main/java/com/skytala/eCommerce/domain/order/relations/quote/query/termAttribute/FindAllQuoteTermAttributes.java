
package com.skytala.eCommerce.domain.order.relations.quote.query.termAttribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quote.event.termAttribute.QuoteTermAttributeFound;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.termAttribute.QuoteTermAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.termAttribute.QuoteTermAttribute;


public class FindAllQuoteTermAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteTermAttribute> returnVal = new ArrayList<QuoteTermAttribute>();
try{
List<GenericValue> results = delegator.findAll("QuoteTermAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteTermAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteTermAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
