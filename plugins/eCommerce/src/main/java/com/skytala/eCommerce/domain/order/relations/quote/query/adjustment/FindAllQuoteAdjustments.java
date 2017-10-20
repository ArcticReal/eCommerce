
package com.skytala.eCommerce.domain.order.relations.quote.query.adjustment;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quote.event.adjustment.QuoteAdjustmentFound;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.adjustment.QuoteAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.adjustment.QuoteAdjustment;


public class FindAllQuoteAdjustments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteAdjustment> returnVal = new ArrayList<QuoteAdjustment>();
try{
List<GenericValue> results = delegator.findAll("QuoteAdjustment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteAdjustmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteAdjustmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
