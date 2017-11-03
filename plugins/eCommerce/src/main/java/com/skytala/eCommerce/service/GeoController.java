package com.skytala.eCommerce.service;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/geo")
public class GeoController {

    @RequestMapping("/country")
    public ResponseEntity getAllCountryGeos() throws GenericEntityException {
        Map filter = UtilMisc.toMap("geoTypeId","COUNTRY");
        return successful(DelegatorFactory.getDelegator("default")
                                          .findByAnd("Geo", filter, null, false));
    }

    @RequestMapping("/{countryGeoId}/regions")
    public ResponseEntity getAllRegionsForCountry(@PathVariable String countryGeoId) throws GenericEntityException {
        Map filter = UtilMisc.toMap("geoId", countryGeoId, "geoAssocTypeId", "REGIONS");
        Delegator delegator = DelegatorFactory.getDelegator("default");
        List<GenericEntity> geoAssocs = delegator.findByAnd("GeoAssoc", filter, null, false);
        List<GenericEntity> geos = new LinkedList<>();
        for(GenericEntity ge : geoAssocs){
            filter = UtilMisc.toMap("geoId", ge.get("geoIdTo"));

            geos.add(delegator.findOne("Geo", filter, false));
        }
        return successful(geos);
    }

}
