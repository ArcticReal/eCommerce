
package com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.event.AgreementTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.mapper.AgreementTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.agreementTypeAttr.model.AgreementTypeAttr;


public class FindAllAgreementTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementTypeAttr> returnVal = new ArrayList<AgreementTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("AgreementTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
