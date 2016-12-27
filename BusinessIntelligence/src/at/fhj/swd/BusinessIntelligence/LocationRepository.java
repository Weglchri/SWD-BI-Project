package at.fhj.swd.BusinessIntelligence;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LocationRepository
{
    public LocationRepository(final EntityManager entityManager)
    {
        setEntityManager( entityManager );
    }

    private EntityManager entityManager;
    public void setEntityManager(final EntityManager entityManager)
    {
        if(entityManager == null)
            throw new IllegalArgumentException("EntityManager is invalid!");

        this.entityManager = entityManager;
    }


    public List<Location> findByCountry(String searchCountry)
    {
        TypedQuery<Location> query =  entityManager.createNamedQuery("findLocationByCountry", Location.class);
        query.setParameter("country", searchCountry);
        return query.getResultList();
    }
}
