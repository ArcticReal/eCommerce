
package com.skytala.eCommerce.domain.product.relations.marketInterest.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.marketInterest.event.MarketInterestFound;
import com.skytala.eCommerce.domain.product.relations.marketInterest.mapper.MarketInterestMapper;
import com.skytala.eCommerce.domain.product.relations.marketInterest.model.MarketInterest;


public class FindAllMarketInterests extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MarketInterest> returnVal = new ArrayList<MarketInterest>();
try{
List<GenericValue> results = delegator.findAll("MarketInterest", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MarketInterestMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MarketInterestFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
