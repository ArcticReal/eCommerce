
package com.skytala.eCommerce.domain.party.relations.partyNameHistory.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyNameHistory.event.PartyNameHistoryFound;
import com.skytala.eCommerce.domain.party.relations.partyNameHistory.mapper.PartyNameHistoryMapper;
import com.skytala.eCommerce.domain.party.relations.partyNameHistory.model.PartyNameHistory;


public class FindAllPartyNameHistorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyNameHistory> returnVal = new ArrayList<PartyNameHistory>();
try{
List<GenericValue> results = delegator.findAll("PartyNameHistory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyNameHistoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyNameHistoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
