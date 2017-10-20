
package com.skytala.eCommerce.domain.party.relations.party.query.oldTaxInfo;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.oldTaxInfo.OldPartyTaxInfoFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.oldTaxInfo.OldPartyTaxInfoMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.oldTaxInfo.OldPartyTaxInfo;


public class FindAllOldPartyTaxInfos extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<OldPartyTaxInfo> returnVal = new ArrayList<OldPartyTaxInfo>();
try{
List<GenericValue> results = delegator.findAll("OldPartyTaxInfo", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(OldPartyTaxInfoMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new OldPartyTaxInfoFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
