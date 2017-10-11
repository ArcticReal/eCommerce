
package com.skytala.eCommerce.domain.party.relations.agreementProductAppl.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.event.AgreementProductApplFound;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.mapper.AgreementProductApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreementProductAppl.model.AgreementProductAppl;


public class FindAllAgreementProductAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementProductAppl> returnVal = new ArrayList<AgreementProductAppl>();
try{
List<GenericValue> results = delegator.findAll("AgreementProductAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementProductApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementProductApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
