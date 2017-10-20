
package com.skytala.eCommerce.domain.party.relations.party.query.contentType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.contentType.PartyContentTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.contentType.PartyContentType;


public class FindAllPartyContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyContentType> returnVal = new ArrayList<PartyContentType>();
try{
List<GenericValue> results = delegator.findAll("PartyContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
