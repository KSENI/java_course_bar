package ru.stqa.addressbook.applicationmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;
import ru.stqa.addressbook.model.PersonData;
import ru.stqa.addressbook.model.Persons;

import java.util.List;

public class DbHelper {
    private SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Persons persons() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<PersonData> result = session.createQuery("from PersonData where deprecated='0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Persons(result);
    }

    public PersonData getContactFromDb(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        PersonData result = (PersonData) session.createQuery("from ContactData where id=" + id).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public GroupData getGroupFromDb(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData result = (GroupData) session.createQuery("from GroupData where id=" + id).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public GroupData getGroupWithMaxIDFromDb() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData result = (GroupData) session.createQuery("from GroupData where id = (select max(group1.id) from GroupData group1)").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Groups getGroupsOfContactFromDb(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from ContactData where group_id=" + id).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public GroupData situatedGroup(Groups groups, PersonData contact) {
        Groups situatedGroups = contact.getGroups();
        for (GroupData group : groups) {
            if (situatedGroups.contains(group)) {
                continue;
            } else {
                return group;
            }
        }
        return null;
    }

    public GroupData situatedGroupForRemoveContact() {
        Groups groups = groups();
        for (GroupData group : groups) {
            if (group.getPersons().size() > 0) {
                return group;
            }
        }
        return null;
    }
}
