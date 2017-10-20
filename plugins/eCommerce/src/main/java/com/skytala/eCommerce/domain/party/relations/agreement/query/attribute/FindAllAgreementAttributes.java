
package com.skytala.eCommerce.domain.party.relations.agreement.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.attribute.AgreementAttributeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.attribute.AgreementAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.attribute.AgreementAttribute;


public class FindAllAgreementAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementAttribute> returnVal = new ArrayList<AgreementAttribute>();
try{
List<GenericValue> results = delegator.findAll("AgreementAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
