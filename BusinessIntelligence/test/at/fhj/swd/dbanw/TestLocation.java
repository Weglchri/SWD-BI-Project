
package at.fhj.swd.dbanw;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestLocation {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "persistence";

    static final String address = "Straße3";
    static final String country = "Mexico";
    static final int zip = 8020;
    static final String city = "Canberra";

    @BeforeClass
    public static void setup() {

        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        assertNotNull(factory);
        manager = factory.createEntityManager();
        assertNotNull(manager);
        transaction = manager.getTransaction();
    }

    @AfterClass
    public static void teardown() {
        if (manager == null) return;
        manager.close();
        factory.close();
    }

    @Test
    public void create() {
        transaction.begin();
        Location testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);
        transaction.commit();
    }

    @Test
    public void modify() {
        Location testAddress = manager.find(Location.class, address);
        assertNotNull(testAddress);
        transaction.begin();
        Location merge = manager.merge(testAddress);
        merge.setCity("Vienna");
        transaction.commit();
        testAddress = manager.find(Location.class, address);
        assertEquals("Vienna" ,testAddress.getCity());
    }

    @Test
    public void remove() {
        Location testAddress = manager.find(Location.class, address);
        assertNotNull(testAddress);
        transaction.begin();
        manager.remove(testAddress);
        transaction.commit();
        testAddress = manager.find(Location.class, address);
        assertEquals(null, testAddress);
    }
}
