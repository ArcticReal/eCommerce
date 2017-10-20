
package com.skytala.eCommerce.domain.order.relations.quote.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quote.event.typeAttr.QuoteTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.typeAttr.QuoteTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.typeAttr.QuoteTypeAttr;


public class FindAllQuoteTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteTypeAttr> returnVal = new ArrayList<QuoteTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("QuoteTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
