
package com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event.PartyAcctgPreferenceFound;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.mapper.PartyAcctgPreferenceMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.model.PartyAcctgPreference;


public class FindAllPartyAcctgPreferences extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyAcctgPreference> returnVal = new ArrayList<PartyAcctgPreference>();
try{
List<GenericValue> results = delegator.findAll("PartyAcctgPreference", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyAcctgPreferenceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyAcctgPreferenceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
