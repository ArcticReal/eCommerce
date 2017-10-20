
package com.skytala.eCommerce.domain.party.relations.party.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.attribute.PartyAttributeFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.attribute.PartyAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.attribute.PartyAttribute;


public class FindAllPartyAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyAttribute> returnVal = new ArrayList<PartyAttribute>();
try{
List<GenericValue> results = delegator.findAll("PartyAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
