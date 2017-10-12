
package com.skytala.eCommerce.domain.humanres.relations.partyQual.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualFound;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.mapper.PartyQualMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.PartyQual;


public class FindAllPartyQuals extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyQual> returnVal = new ArrayList<PartyQual>();
try{
List<GenericValue> results = delegator.findAll("PartyQual", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyQualMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyQualFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
