
package com.skytala.eCommerce.domain.party.relations.partyRelationshipType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyRelationshipType.event.PartyRelationshipTypeFound;
import com.skytala.eCommerce.domain.party.relations.partyRelationshipType.mapper.PartyRelationshipTypeMapper;
import com.skytala.eCommerce.domain.party.relations.partyRelationshipType.model.PartyRelationshipType;


public class FindAllPartyRelationshipTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyRelationshipType> returnVal = new ArrayList<PartyRelationshipType>();
try{
List<GenericValue> results = delegator.findAll("PartyRelationshipType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyRelationshipTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyRelationshipTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
