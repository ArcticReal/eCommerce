
package com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.event.AgreementItemAttributeFound;
import com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.mapper.AgreementItemAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.agreementItemAttribute.model.AgreementItemAttribute;


public class FindAllAgreementItemAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementItemAttribute> returnVal = new ArrayList<AgreementItemAttribute>();
try{
List<GenericValue> results = delegator.findAll("AgreementItemAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementItemAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementItemAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
