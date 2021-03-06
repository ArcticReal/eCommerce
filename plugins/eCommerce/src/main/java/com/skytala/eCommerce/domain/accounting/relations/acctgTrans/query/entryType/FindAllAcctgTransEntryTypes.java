
package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.query.entryType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.entryType.AcctgTransEntryTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entryType.AcctgTransEntryType;


public class FindAllAcctgTransEntryTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AcctgTransEntryType> returnVal = new ArrayList<AcctgTransEntryType>();
try{
List<GenericValue> results = delegator.findAll("AcctgTransEntryType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AcctgTransEntryTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AcctgTransEntryTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
