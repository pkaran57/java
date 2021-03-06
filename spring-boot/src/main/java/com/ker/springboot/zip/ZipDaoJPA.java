package com.ker.springboot.zip;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ZipDaoJPA {

    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(ZipDaoJPA.class);
    @PersistenceContext  // entity manager is interface to persistence context
    @Autowired
    EntityManager entityManager;

    public Zip getZip(final int zipCode){
        log.debug("Looking up info for {} zipcode", zipCode);

        log.debug("Fetching zip data from DB");
        return entityManager.find(Zip.class, zipCode);
    }

    public void deleteZip(final int zipToDelete){
        Zip zip = this.getZip(zipToDelete);
        entityManager.remove(zip);
    }

    public void addZip(final int zip, final String locationData){
        entityManager.merge(Zip.builder().zip(zip).locationInfo(locationData).build());
    }

    public void updateZipData(final int zip, final String newLocationData){
        entityManager.merge(Zip.builder().zip(zip).locationInfo(newLocationData).build());
    }

    public List<Zip> getDataForAllZips(){
        TypedQuery<Zip> namedQuery = entityManager.createNamedQuery("getAllZips", Zip.class);
        return namedQuery.getResultList();
    }
}
