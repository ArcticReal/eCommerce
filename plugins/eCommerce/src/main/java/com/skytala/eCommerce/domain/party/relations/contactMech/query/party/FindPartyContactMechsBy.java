package com.skytala.eCommerce.domain.party.relations.contactMech.query.party;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.party.PartyContactMechFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.party.PartyContactMechMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;

public class FindPartyContactMechsBy extends Query {


    Map<String, String> filter;
    public FindPartyContactMechsBy(Map<String, String> filter) {
        this.filter = filter;
    }

    @Override
    public Event execute(){

        Delegator delegator = DelegatorFactory.getDelegator("default");
        List<PartyContactMech> foundPartyContactMechs = new ArrayList<PartyContactMech>();

        try{

            List<GenericValue> buf = new LinkedList<>();

            buf = delegator.findAll("PartyContactMech", false);


            for (int i = 0; i < buf.size(); i++) {
                if(applysToFilter(buf.get(i))) {
                    foundPartyContactMechs.add(PartyContactMechMapper.map(buf.get(i)));
                }
            }


        }catch(GenericEntityException e) {
            e.printStackTrace();
        }
        Event resultingEvent = new PartyContactMechFound(foundPartyContactMechs);
        Broker.instance().publish(resultingEvent);
        return resultingEvent;

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
    public void setFilter(Map<String, String> newFilter) {
        this.filter = newFilter;
    }
}
