package br.com.elementi.core.qtable;

import br.com.elementi.test.DummyEntity;
import br.com.elementi.test.DummyIdentityEntity;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Created by eltonsolid on 17/12/16.
 */
public class QTable2Test {

    public QTable2 table;
    public DummyEntity.Entity entity;

    @Before
    public void before() throws Exception {
        Session session = Mockito.mock(Session.class);
        table = QTable2.of(DummyEntity.class, session);
        entity = null;//QEntity.of(DummyEntity.Entity.class, table);
        DummyEntity.create(DummyIdentityEntity.create(1)).name("Eltonsolid").idade(34).get();
    }

    @Test
    public void testQuery() throws Exception {
        String query = table.eq("name", "Eltonsolid").query();
        assertEquals("FROM DummyEntity alias WHERE alias.name = :name$683938559", query);
    }

    @Test
    public void testEntityQuery() throws Exception {
        String query = entity.name("Eltonsolid").query();
        assertEquals("FROM DummyEntity alias WHERE alias.name = :name$683938559", query);
    }

    @Test
    public void testAnd() throws Exception {
        String query = table.eq("name", "Eltonsolid").eq("name", "Vanessa").query();
        assertEquals("FROM DummyEntity alias WHERE alias.name = :name$683938559 AND alias.name = :name$1897643263", query);
    }

    @Test
    public void testEntityAnd() throws Exception {
        String query = entity.name("Eltonsolid").name("Vanessa").query();
        assertEquals("FROM DummyEntity alias WHERE alias.name = :name$683938559 AND alias.name = :name$1897643263", query);
    }

    @Test
    public void testOr() throws Exception {
        String query = table.eq("name", "Eltonsolid").or().eq("name", "Vanessa").query();
        assertEquals("FROM DummyEntity alias WHERE alias.name = :name$683938559 OR alias.name = :name$1897643263", query);
    }

    @Test
    public void testEntityOr() throws Exception {
        String query = entity.name("Eltonsolid").or().name("Vanessa").query();
        assertEquals("FROM DummyEntity alias WHERE alias.name = :name$683938559 OR alias.name = :name$1897643263", query);
    }


    @Test
    public void testEntityOrWithAnd() throws Exception {
        String query = entity.name("Eltonsolid").or().name("Vanessa").idade(30).query();
        assertEquals("FROM DummyEntity alias WHERE alias.name = :name$683938559 OR alias.name = :name$1897643263 AND alias.idade = :idade$30", query);
    }


    @Test
    public void testSubValueQuery() throws Exception {
        String query = table.eq("address.value", "Rua").query();
        assertEquals("FROM DummyEntity alias WHERE alias.address.value = :address_value$82526", query);
    }

    @Test
    public void testJoin() throws Exception {
        String query = table.select("name").query();
        assertEquals("SELECT alias.name FROM DummyEntity alias", query);
    }

    @Test
    public void testSelectManyValue() throws Exception {
        String query = table.select("name", "nikename", "email").query();
        assertEquals("SELECT alias.name, alias.nikename, alias.email FROM DummyEntity alias", query);
    }

    @Test
    public void testCountAndDistinctValue() throws Exception {
        String query = table.count().distinct().select("name").query();
        assertEquals("SELECT DISTINCT COUNT alias.name FROM DummyEntity alias", query);
    }


}
