
package com.skytala.eCommerce.domain.party.relations.partyTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.partyTypeAttr.event.PartyTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.partyTypeAttr.mapper.PartyTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.partyTypeAttr.model.PartyTypeAttr;


public class FindAllPartyTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyTypeAttr> returnVal = new ArrayList<PartyTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("PartyTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
