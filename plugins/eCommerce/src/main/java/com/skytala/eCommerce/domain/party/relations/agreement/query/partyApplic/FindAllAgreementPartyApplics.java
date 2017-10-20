
package com.skytala.eCommerce.domain.party.relations.agreement.query.partyApplic;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.partyApplic.AgreementPartyApplicFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.partyApplic.AgreementPartyApplicMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.partyApplic.AgreementPartyApplic;


public class FindAllAgreementPartyApplics extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementPartyApplic> returnVal = new ArrayList<AgreementPartyApplic>();
try{
List<GenericValue> results = delegator.findAll("AgreementPartyApplic", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementPartyApplicMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementPartyApplicFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
