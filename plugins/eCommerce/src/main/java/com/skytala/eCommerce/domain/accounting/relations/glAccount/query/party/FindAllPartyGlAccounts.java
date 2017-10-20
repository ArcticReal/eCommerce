
package com.skytala.eCommerce.domain.accounting.relations.glAccount.query.party;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.party.PartyGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.party.PartyGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.party.PartyGlAccount;


public class FindAllPartyGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyGlAccount> returnVal = new ArrayList<PartyGlAccount>();
try{
List<GenericValue> results = delegator.findAll("PartyGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
