package com.skytala.eCommerce.query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Person;
import com.skytala.eCommerce.entity.PersonMapper;
import com.skytala.eCommerce.event.PersonFound;

public class FindPersonsBy implements Query {


Map<String, String> filter;
public FindPersonsBy(Map<String, String> filter) {
this.filter = filter;
}

@Override
public void execute(){

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Person> foundPersons = new ArrayList<Person>();

try{
List<GenericValue> buf = delegator.findAll("Person", false);
for (int i = 0; i < buf.size(); i++) {
if(applysToFilter(buf.get(i))) {
foundPersons.add(PersonMapper.map(buf.get(i)));
}
}


}catch(GenericEntityException e) {
e.printStackTrace();
}
Broker.instance().publish(new PersonFound(foundPersons));


}
public boolean applysToFilter(GenericValue val) {

Iterator<String> iterator = filter.keySet().iterator();

while(iterator.hasNext()) {

String key = iterator.next();

if(val.get(key) == null) {
return false;
}

if((val.get(key).toString()).contains(filter.get(key))) {
}else {
return false;
}
}
return true;
}
}
