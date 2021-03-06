
package com.skytala.eCommerce.domain.order.relations.custRequest.query.party;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.party.CustRequestPartyFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.party.CustRequestPartyMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.party.CustRequestParty;


public class FindAllCustRequestPartys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestParty> returnVal = new ArrayList<CustRequestParty>();
try{
List<GenericValue> results = delegator.findAll("CustRequestParty", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestPartyMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestPartyFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
