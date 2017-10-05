
package com.skytala.eCommerce.domain.settlementTerm.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.settlementTerm.event.SettlementTermFound;
import com.skytala.eCommerce.domain.settlementTerm.mapper.SettlementTermMapper;
import com.skytala.eCommerce.domain.settlementTerm.model.SettlementTerm;


public class FindAllSettlementTerms extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SettlementTerm> returnVal = new ArrayList<SettlementTerm>();
try{
List<GenericValue> results = delegator.findAll("SettlementTerm", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SettlementTermMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SettlementTermFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
