
package com.skytala.eCommerce.domain.order.relations.quoteItem.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quoteItem.event.QuoteItemFound;
import com.skytala.eCommerce.domain.order.relations.quoteItem.mapper.QuoteItemMapper;
import com.skytala.eCommerce.domain.order.relations.quoteItem.model.QuoteItem;


public class FindAllQuoteItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteItem> returnVal = new ArrayList<QuoteItem>();
try{
List<GenericValue> results = delegator.findAll("QuoteItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
