
package com.skytala.eCommerce.domain.party.relations.agreementContent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreementContent.event.AgreementContentFound;
import com.skytala.eCommerce.domain.party.relations.agreementContent.mapper.AgreementContentMapper;
import com.skytala.eCommerce.domain.party.relations.agreementContent.model.AgreementContent;


public class FindAllAgreementContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementContent> returnVal = new ArrayList<AgreementContent>();
try{
List<GenericValue> results = delegator.findAll("AgreementContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
