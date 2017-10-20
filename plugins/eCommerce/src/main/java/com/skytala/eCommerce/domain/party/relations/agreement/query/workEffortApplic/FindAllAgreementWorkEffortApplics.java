
package com.skytala.eCommerce.domain.party.relations.agreement.query.workEffortApplic;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.workEffortApplic.AgreementWorkEffortApplicFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.workEffortApplic.AgreementWorkEffortApplicMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.workEffortApplic.AgreementWorkEffortApplic;


public class FindAllAgreementWorkEffortApplics extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementWorkEffortApplic> returnVal = new ArrayList<AgreementWorkEffortApplic>();
try{
List<GenericValue> results = delegator.findAll("AgreementWorkEffortApplic", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementWorkEffortApplicMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementWorkEffortApplicFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
