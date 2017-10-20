
package com.skytala.eCommerce.domain.party.relations.agreement.query.promoAppl;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.promoAppl.AgreementPromoApplFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.promoAppl.AgreementPromoApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.promoAppl.AgreementPromoAppl;


public class FindAllAgreementPromoAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementPromoAppl> returnVal = new ArrayList<AgreementPromoAppl>();
try{
List<GenericValue> results = delegator.findAll("AgreementPromoAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementPromoApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementPromoApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
