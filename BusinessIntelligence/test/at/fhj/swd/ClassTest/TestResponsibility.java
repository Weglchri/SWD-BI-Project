package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.Helper.Handler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class TestResponsibility extends Handler
{
    private static Projectmanager projectmanager1;
    private static Projectmanager projectmanager2;
    private static Projectmanager projectmanager3;
    private static Project projectWebsite;
    private static Project projectQualityCheck;


    @BeforeClass
    public static void setup() {
        Handler.build();
        Handler.init();
    }

    @AfterClass
    public static void teardown() {
        Handler.close();
        Handler.destroy();
    }

    @Test
    public void create()
    {
        transaction.begin();

        projectmanager1 = new Projectmanager(name, email, password, involved, function, null);
        projectmanager2 = new Projectmanager(name2, email2, password2, involved2, function2, null);
        projectmanager3 = new Projectmanager(name3, email3, password3, involved3, function3, null);
        assertNotNull(projectmanager1);
        assertNotNull(projectmanager2);
        assertNotNull(projectmanager3);
        manager.persist(projectmanager1);
        manager.persist(projectmanager2);
        manager.persist(projectmanager3);


        projectWebsite = new Project(capital, date, task, null);
        projectQualityCheck = new Project(capital, date, task, null);
        assertNotNull(projectWebsite);
        assertNotNull(projectQualityCheck);
        manager.persist(projectWebsite);
        manager.persist(projectQualityCheck);

        projectmanager1.addProject(projectWebsite);
        projectmanager1.addProject(projectQualityCheck);
        projectmanager2.addProject(projectWebsite);
        projectmanager3.addProject(projectQualityCheck);

        transaction.commit();


        assertFalse(projectmanager2.getProjects().contains(projectQualityCheck));
        assertFalse(projectmanager3.getProjects().contains(projectWebsite));
        assertTrue(projectmanager1.getProjects().contains(projectWebsite));
        assertTrue(projectmanager3.getProjects().contains(projectQualityCheck));

        assertTrue(projectWebsite.getProjectmanagers().contains(projectmanager1));
        assertTrue(projectQualityCheck.getProjectmanagers().contains(projectmanager1));
        assertFalse(projectQualityCheck.getProjectmanagers().contains(projectmanager2));
        assertFalse(projectWebsite.getProjectmanagers().contains(projectmanager3));

    }

    @Test
    public void remove() {

        transaction.begin();

        manager.remove(projectmanager1);
        manager.remove(projectmanager2);
        manager.remove(projectmanager3);
        manager.remove(projectWebsite);
        manager.remove(projectQualityCheck);

        transaction.commit();

        projectWebsite = manager.find(Project.class, projectWebsite.getProjectId());
        projectQualityCheck =  manager.find(Project.class, projectQualityCheck.getProjectId());
        projectmanager1 = manager.find(Projectmanager.class, projectmanager1.getUserId());
        projectmanager2 = manager.find(Projectmanager.class, projectmanager2.getUserId());
        projectmanager3 = manager.find(Projectmanager.class, projectmanager3.getUserId());

        assertNull(projectmanager1);
        assertNull(projectmanager2);
        assertNull(projectmanager3);
        assertNull(projectWebsite);
        assertNull(projectQualityCheck);

    }

}

