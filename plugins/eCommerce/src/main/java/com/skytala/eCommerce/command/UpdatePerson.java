package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Person;
import com.skytala.eCommerce.event.PersonUpdated;

public class UpdatePerson implements Command {

private Person elementToBeUpdated;

public UpdatePerson(Person elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Person getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Person elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public void execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Person", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
success = false;
}
Broker.instance().publish(new PersonUpdated(success));
}
}
