
package com.skytala.eCommerce.domain.party.relations.agreementContentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreementContentType.event.AgreementContentTypeFound;
import com.skytala.eCommerce.domain.party.relations.agreementContentType.mapper.AgreementContentTypeMapper;
import com.skytala.eCommerce.domain.party.relations.agreementContentType.model.AgreementContentType;


public class FindAllAgreementContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementContentType> returnVal = new ArrayList<AgreementContentType>();
try{
List<GenericValue> results = delegator.findAll("AgreementContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
