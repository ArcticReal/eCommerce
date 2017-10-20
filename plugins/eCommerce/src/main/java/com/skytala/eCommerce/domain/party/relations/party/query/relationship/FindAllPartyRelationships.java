
package com.skytala.eCommerce.domain.party.relations.party.query.relationship;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.relationship.PartyRelationshipFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.relationship.PartyRelationshipMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.relationship.PartyRelationship;


public class FindAllPartyRelationships extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyRelationship> returnVal = new ArrayList<PartyRelationship>();
try{
List<GenericValue> results = delegator.findAll("PartyRelationship", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyRelationshipMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyRelationshipFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
