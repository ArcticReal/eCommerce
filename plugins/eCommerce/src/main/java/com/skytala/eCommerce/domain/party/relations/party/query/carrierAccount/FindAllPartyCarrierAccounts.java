
package com.skytala.eCommerce.domain.party.relations.party.query.carrierAccount;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.carrierAccount.PartyCarrierAccountFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.carrierAccount.PartyCarrierAccountMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.carrierAccount.PartyCarrierAccount;


public class FindAllPartyCarrierAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyCarrierAccount> returnVal = new ArrayList<PartyCarrierAccount>();
try{
List<GenericValue> results = delegator.findAll("PartyCarrierAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyCarrierAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyCarrierAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
