
package com.skytala.eCommerce.domain.accounting.relations.partyRate.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.event.PartyRateFound;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.mapper.PartyRateMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyRate.model.PartyRate;


public class FindAllPartyRates extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyRate> returnVal = new ArrayList<PartyRate>();
try{
List<GenericValue> results = delegator.findAll("PartyRate", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyRateMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyRateFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
