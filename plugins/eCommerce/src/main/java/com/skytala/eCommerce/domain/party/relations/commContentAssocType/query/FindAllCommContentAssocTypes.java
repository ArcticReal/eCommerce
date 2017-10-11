
package com.skytala.eCommerce.domain.party.relations.commContentAssocType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.event.CommContentAssocTypeFound;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.mapper.CommContentAssocTypeMapper;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.model.CommContentAssocType;


public class FindAllCommContentAssocTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CommContentAssocType> returnVal = new ArrayList<CommContentAssocType>();
try{
List<GenericValue> results = delegator.findAll("CommContentAssocType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CommContentAssocTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CommContentAssocTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
