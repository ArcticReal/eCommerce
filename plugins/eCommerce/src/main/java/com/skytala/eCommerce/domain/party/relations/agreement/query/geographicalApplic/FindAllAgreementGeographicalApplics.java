
package com.skytala.eCommerce.domain.party.relations.agreement.query.geographicalApplic;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.geographicalApplic.AgreementGeographicalApplicFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.geographicalApplic.AgreementGeographicalApplicMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.geographicalApplic.AgreementGeographicalApplic;


public class FindAllAgreementGeographicalApplics extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementGeographicalApplic> returnVal = new ArrayList<AgreementGeographicalApplic>();
try{
List<GenericValue> results = delegator.findAll("AgreementGeographicalApplic", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementGeographicalApplicMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementGeographicalApplicFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
