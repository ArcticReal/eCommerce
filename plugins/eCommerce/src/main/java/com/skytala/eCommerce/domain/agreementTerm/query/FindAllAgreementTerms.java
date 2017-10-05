
package com.skytala.eCommerce.domain.agreementTerm.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.agreementTerm.event.AgreementTermFound;
import com.skytala.eCommerce.domain.agreementTerm.mapper.AgreementTermMapper;
import com.skytala.eCommerce.domain.agreementTerm.model.AgreementTerm;


public class FindAllAgreementTerms extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementTerm> returnVal = new ArrayList<AgreementTerm>();
try{
List<GenericValue> results = delegator.findAll("AgreementTerm", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementTermMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementTermFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
