
package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event.PartyTaxAuthInfoFound;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.mapper.PartyTaxAuthInfoMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;


public class FindAllPartyTaxAuthInfos extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyTaxAuthInfo> returnVal = new ArrayList<PartyTaxAuthInfo>();
try{
List<GenericValue> results = delegator.findAll("PartyTaxAuthInfo", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyTaxAuthInfoMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyTaxAuthInfoFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
