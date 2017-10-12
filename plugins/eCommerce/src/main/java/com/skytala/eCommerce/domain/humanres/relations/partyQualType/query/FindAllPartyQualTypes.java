
package com.skytala.eCommerce.domain.humanres.relations.partyQualType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.partyQualType.event.PartyQualTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.partyQualType.mapper.PartyQualTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyQualType.model.PartyQualType;


public class FindAllPartyQualTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyQualType> returnVal = new ArrayList<PartyQualType>();
try{
List<GenericValue> results = delegator.findAll("PartyQualType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyQualTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyQualTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
