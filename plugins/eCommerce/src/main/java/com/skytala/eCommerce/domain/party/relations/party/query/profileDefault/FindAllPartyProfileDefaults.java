
package com.skytala.eCommerce.domain.party.relations.party.query.profileDefault;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.profileDefault.PartyProfileDefaultMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.profileDefault.PartyProfileDefault;


public class FindAllPartyProfileDefaults extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyProfileDefault> returnVal = new ArrayList<PartyProfileDefault>();
try{
List<GenericValue> results = delegator.findAll("PartyProfileDefault", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyProfileDefaultMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyProfileDefaultFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
