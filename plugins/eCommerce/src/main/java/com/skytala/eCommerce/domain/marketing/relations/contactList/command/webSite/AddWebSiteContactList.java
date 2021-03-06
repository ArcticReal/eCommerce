package com.skytala.eCommerce.domain.marketing.relations.contactList.command.webSite;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.webSite.WebSiteContactListAdded;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.webSite.WebSiteContactListMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.webSite.WebSiteContactList;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWebSiteContactList extends Command {

private WebSiteContactList elementToBeAdded;
public AddWebSiteContactList(WebSiteContactList elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WebSiteContactList addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WebSiteContactList", elementToBeAdded.mapAttributeField());
addedElement = WebSiteContactListMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WebSiteContactListAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
