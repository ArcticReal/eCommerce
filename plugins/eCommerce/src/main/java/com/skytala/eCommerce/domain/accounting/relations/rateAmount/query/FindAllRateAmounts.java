
package com.skytala.eCommerce.domain.accounting.relations.rateAmount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.event.RateAmountFound;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.mapper.RateAmountMapper;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.model.RateAmount;


public class FindAllRateAmounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RateAmount> returnVal = new ArrayList<RateAmount>();
try{
List<GenericValue> results = delegator.findAll("RateAmount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RateAmountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RateAmountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
