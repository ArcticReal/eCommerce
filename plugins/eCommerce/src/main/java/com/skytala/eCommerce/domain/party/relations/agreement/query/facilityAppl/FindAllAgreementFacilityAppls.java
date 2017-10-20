
package com.skytala.eCommerce.domain.party.relations.agreement.query.facilityAppl;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.facilityAppl.AgreementFacilityApplFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.facilityAppl.AgreementFacilityApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.facilityAppl.AgreementFacilityAppl;


public class FindAllAgreementFacilityAppls extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementFacilityAppl> returnVal = new ArrayList<AgreementFacilityAppl>();
try{
List<GenericValue> results = delegator.findAll("AgreementFacilityAppl", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementFacilityApplMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementFacilityApplFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
