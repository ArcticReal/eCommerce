
package com.skytala.eCommerce.domain.quantityBreak.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.quantityBreak.event.QuantityBreakFound;
import com.skytala.eCommerce.domain.quantityBreak.mapper.QuantityBreakMapper;
import com.skytala.eCommerce.domain.quantityBreak.model.QuantityBreak;


public class FindAllQuantityBreaks extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuantityBreak> returnVal = new ArrayList<QuantityBreak>();
try{
List<GenericValue> results = delegator.findAll("QuantityBreak", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuantityBreakMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuantityBreakFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
