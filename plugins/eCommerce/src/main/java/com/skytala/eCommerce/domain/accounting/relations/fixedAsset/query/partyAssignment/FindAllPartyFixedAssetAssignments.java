
package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.query.partyAssignment;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentFound;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.partyAssignment.PartyFixedAssetAssignmentMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment.PartyFixedAssetAssignment;


public class FindAllPartyFixedAssetAssignments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyFixedAssetAssignment> returnVal = new ArrayList<PartyFixedAssetAssignment>();
try{
List<GenericValue> results = delegator.findAll("PartyFixedAssetAssignment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyFixedAssetAssignmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyFixedAssetAssignmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
