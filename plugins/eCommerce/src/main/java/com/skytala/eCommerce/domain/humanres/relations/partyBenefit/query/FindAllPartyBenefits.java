
package com.skytala.eCommerce.domain.humanres.relations.partyBenefit.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event.PartyBenefitFound;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.mapper.PartyBenefitMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.model.PartyBenefit;


public class FindAllPartyBenefits extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyBenefit> returnVal = new ArrayList<PartyBenefit>();
try{
List<GenericValue> results = delegator.findAll("PartyBenefit", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyBenefitMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyBenefitFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
