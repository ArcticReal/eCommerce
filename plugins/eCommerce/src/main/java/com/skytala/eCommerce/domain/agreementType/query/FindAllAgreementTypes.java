
package com.skytala.eCommerce.domain.agreementType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.agreementType.event.AgreementTypeFound;
import com.skytala.eCommerce.domain.agreementType.mapper.AgreementTypeMapper;
import com.skytala.eCommerce.domain.agreementType.model.AgreementType;


public class FindAllAgreementTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementType> returnVal = new ArrayList<AgreementType>();
try{
List<GenericValue> results = delegator.findAll("AgreementType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
