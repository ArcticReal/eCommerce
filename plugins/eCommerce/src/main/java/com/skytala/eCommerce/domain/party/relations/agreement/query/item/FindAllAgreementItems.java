
package com.skytala.eCommerce.domain.party.relations.agreement.query.item;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.agreement.event.item.AgreementItemFound;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.item.AgreementItemMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.item.AgreementItem;


public class FindAllAgreementItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AgreementItem> returnVal = new ArrayList<AgreementItem>();
try{
List<GenericValue> results = delegator.findAll("AgreementItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
