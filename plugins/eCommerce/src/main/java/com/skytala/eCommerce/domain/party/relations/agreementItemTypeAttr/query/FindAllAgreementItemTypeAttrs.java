
package com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.event.AgreementItemTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.mapper.AgreementItemTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.agreementItemTypeAttr.model.AgreementItemTypeAttr;


public class FindAllAgreementItemTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementItemTypeAttr> returnVal = new ArrayList<AgreementItemTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("AgreementItemTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementItemTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementItemTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
