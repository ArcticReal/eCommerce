
package com.skytala.eCommerce.domain.party.relations.agreement.query.employmentAppl;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.employmentAppl.AgreementEmploymentApplFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.employmentAppl.AgreementEmploymentApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.employmentAppl.AgreementEmploymentAppl;


public class FindAllAgreementEmploymentAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementEmploymentAppl> returnVal = new ArrayList<AgreementEmploymentAppl>();
try{
List<GenericValue> results = delegator.findAll("AgreementEmploymentAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementEmploymentApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementEmploymentApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
