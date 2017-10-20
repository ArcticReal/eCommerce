
package com.skytala.eCommerce.domain.party.relations.agreement.query.itemType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.itemType.AgreementItemTypeFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.itemType.AgreementItemTypeMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.itemType.AgreementItemType;


public class FindAllAgreementItemTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementItemType> returnVal = new ArrayList<AgreementItemType>();
try{
List<GenericValue> results = delegator.findAll("AgreementItemType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementItemTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementItemTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
