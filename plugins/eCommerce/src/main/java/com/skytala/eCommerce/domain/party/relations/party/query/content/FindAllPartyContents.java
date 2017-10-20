
package com.skytala.eCommerce.domain.party.relations.party.query.content;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.content.PartyContentFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.content.PartyContentMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.content.PartyContent;


public class FindAllPartyContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyContent> returnVal = new ArrayList<PartyContent>();
try{
List<GenericValue> results = delegator.findAll("PartyContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
