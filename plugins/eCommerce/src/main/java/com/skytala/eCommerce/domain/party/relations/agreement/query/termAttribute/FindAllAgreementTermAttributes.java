
package com.skytala.eCommerce.domain.party.relations.agreement.query.termAttribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.termAttribute.AgreementTermAttributeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.termAttribute.AgreementTermAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.termAttribute.AgreementTermAttribute;


public class FindAllAgreementTermAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementTermAttribute> returnVal = new ArrayList<AgreementTermAttribute>();
try{
List<GenericValue> results = delegator.findAll("AgreementTermAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementTermAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementTermAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
