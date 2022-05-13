package br.com.elementi.core.qtable;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import br.com.elementi.core.tools.BuilderEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.elementi.core.exception.NotFoundException;
import br.com.elementi.core.exception.NotUniqueFoundException;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.qtable.QOptional;
import br.com.elementi.core.qtable.QTable;
import br.com.elementi.test.DummyAddress;
import br.com.elementi.test.DummyContract;
import br.com.elementi.test.DummyEntity;
import br.com.elementi.test.DummyIdentityEntity;
import br.com.elementi.test.DummyPerson;
import br.com.elementi.test.DummyQSearch;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class QTableTest {
    private QTable table;
    public DummyEntity.Entity entity;
    private DummyEntity dummyEntity;
    private Query query;

    @Before
    public void before() throws Exception {
        Session session = Mockito.mock(Session.class);
        query = mock(Query.class);
        dummyEntity = new DummyEntity();
        dummyEntity.id = DummyIdentityEntity.create(0);
        when(query.list()).thenReturn(Lists.newArrayList(dummyEntity));
        when(session.createQuery(anyString())).thenReturn(query);
        table = QTable.of(DummyEntity.class, session);
        entity = QEntity.of(DummyEntity.Entity.class, table);
    }

    @Test
    public void testFrom() throws Exception {
        Class<?> from = table.from().entityClass();
        assertEquals(DummyEntity.class, from);
    }

    @Test
    public void testQuery() throws Exception {
        String query = table.query();
        assertEquals("FROM DummyEntity dummyEntity", query);
    }


    @Test
    public void testEqual() throws Exception {
        String query = table.eq("name", "Tom").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name = :name$Tom", query);
    }

    @Ignore
    @Test(expected = NotFoundException.class)
    public void testEqualWithInvalidField() throws Exception {
        table.eq("", "Tom").query();
    }

    @Test
    public void testLike() throws Exception {
        String query = table.lk("name", "Tom").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name LIKE :name$%Tom%", query);
    }

    @Test
    public void testLikeStart() throws Exception {
        String query = table.lkStart("name", "Tom").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name LIKE :name$Tom%", query);
    }

    @Test
    public void testLikeEnd() throws Exception {
        String query = table.lkEnd("name", "Tom").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name LIKE :name$%Tom", query);
    }


    @Test
    public void testAnd() throws Exception {
        String query = table.eq("name", "Tom").lk("parent", "Harry").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name = :name$Tom AND dummyEntity.parent LIKE :parent$%Harry%", query);
    }

    @Test
    public void testOR() throws Exception {
        String query = table.eq("name", "Tom").or().lk("name", "Ridley").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name = :name$Tom OR dummyEntity.name LIKE :name$%Ridley%", query);
    }

    @Test
    public void testORCombinate() throws Exception {
        String query = table.eq("name", "Tom").or().lk("name", "Ridley").or().lk("parentName", "Valmiro").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name = :name$Tom OR dummyEntity.name LIKE :name$%Ridley% OR dummyEntity.parentName LIKE :parentName$%Valmiro%", query);
    }

    @Test
    public void testSubLevelEntity() throws Exception {
        String query = table.eq("address.value", "Rua").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.address.value = :address_value$Rua", query);
    }

    @Test
    public void testBetween() throws Exception {
        String query = table.between("age", 10, 20).query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.age BETWEEN :age$1 AND :age$2", query);
    }

    @Test
    public void testBetWeenAndEqual() throws Exception {
        String query = table.between("age", 10, 20).eq("name", "Tom").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.age BETWEEN :age$1 AND :age$2 AND dummyEntity.name = :name$Tom", query);
    }

    @Test
    public void testGreaterThan() throws Exception {
        String query = table.gt("age", 20).query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.age >= :age$20", query);
    }

    @Test
    public void testLesserThan() throws Exception {
        String query = table.lt("age", 20).query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.age <= :age$20", query);
    }

    @Test
    public void testIn() throws Exception {
        String query = table.in("age", 20).query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.age IN (:age$[20])", query);
    }

    @Ignore
    @Test
    public void testLenth() throws Exception {
        String query = table.lenth("name", 20).query();
        assertEquals("", query);
    }

    @Test
    public void testDistinct() throws Exception {
        String query = table.distinct().query();
        assertEquals("SELECT DISTINCT dummyEntity FROM DummyEntity dummyEntity", query);
    }

    @Test
    public void testFetch() throws Exception {
        String query = table.fetch("itens").query();
        assertEquals("SELECT DISTINCT dummyEntity FROM DummyEntity dummyEntity FETCH ALL dummyEntity.itens", query);
    }

    @Test
    public void testTwoFecth() throws Exception {
        String query = table.fetch("itens").fetch("values").query();
        assertEquals("SELECT DISTINCT dummyEntity FROM DummyEntity dummyEntity FETCH ALL dummyEntity.itens FETCH ALL dummyEntity.values", query);
    }

    @Test
    public void testCount() throws Exception {
        String query = table.count().query();
        assertEquals("SELECT COUNT dummyEntity FROM DummyEntity dummyEntity", query);
    }

    @Test
    public void testCountAndDistinct() throws Exception {
        String query = table.distinct().count().query();
        assertEquals("SELECT DISTINCT COUNT dummyEntity FROM DummyEntity dummyEntity", query);
    }

    @Test
    public void testSelectValue() throws Exception {
        String query = table.select("name").query();
        assertEquals("SELECT dummyEntity.name FROM DummyEntity dummyEntity", query);
    }

    @Test
    public void testSelectManyValue() throws Exception {
        String query = table.select("name", "nikename", "email").query();
        assertEquals("SELECT dummyEntity.name, dummyEntity.nikename, dummyEntity.email FROM DummyEntity dummyEntity", query);
    }

    @Test
    public void testCountAndDistinctValue() throws Exception {
        String query = table.count().distinct().select("name").query();
        assertEquals("SELECT DISTINCT COUNT dummyEntity.name FROM DummyEntity dummyEntity", query);
    }

    @Test
    public void testComplexSituation() throws Exception {
        String query = table.eq("name", "Eltonsolid").between("age", 25, 29).lk("parent.name", "Valmiro").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name = :name$Eltonsolid AND dummyEntity.age BETWEEN :age$1 AND :age$2 AND dummyEntity.parent.name LIKE :parent_name$%Valmiro%",
                query);
    }

    @Test
    public void testSearchAgregate() throws Exception {
        String query = QTable.of(new DummyQSearch(), Mockito.mock(Session.class)).query();
        assertEquals("FROM DummyPerson dummyPerson WHERE dummyPerson.name LIKE :name$%Eltonsolid% AND dummyPerson.age = :age$30 AND dummyPerson.contact.number = :contact_number$991676621", query);
    }

    @Test
    public void testJoin() throws Exception {
        String query = table.join(DummyContract.class).query();
        assertEquals("FROM DummyEntity dummyEntity, DummyContract dummyContract WHERE dummyEntity.id = dummyContract.identityEntity", query);
    }

    @Test
    public void testJoinJoinColumn() throws Exception {
        String query = table.join(DummyPerson.class).query();
        assertEquals("FROM DummyEntity dummyEntity, DummyPerson dummyPerson WHERE dummyEntity.id = dummyPerson.entity.id", query);
    }

    @Test
    public void testJoinComplexSituation() throws Exception {
        String query = table.select("name", "father").eq("name", "Elton").join(DummyPerson.class).select("address.name", "phone.number")
                .join(DummyContract.class).select("code", "type").query();
        assertEquals("SELECT dummyEntity.name, dummyEntity.father, dummyPerson.address.name, dummyPerson.phone.number, dummyContract.code, dummyContract.type "
                + "FROM DummyEntity dummyEntity, DummyPerson dummyPerson, DummyContract dummyContract " + "WHERE dummyEntity.name = :name$Elton " + "AND dummyEntity.id = dummyPerson.entity.id "
                + "AND dummyEntity.id = dummyContract.identityEntity", query);
    }

    @Test(expected = NotFoundException.class)
    public void testJoinFail() throws Exception {
        table.join(DummyAddress.class).query();
    }

    @Test
    public void testFind() throws Exception {
        DummyEntity found = table.find();
        assertEquals(dummyEntity.getId(), found.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFound() throws Exception {
        when(query.list()).thenReturn(Lists.newArrayList());
        table.find();
    }

    @Test
    public void testOptional() throws Exception {
        when(query.list()).thenReturn(Lists.newArrayList());
        QOptional findOptional = table.findOptional();
        assertFalse(findOptional.isFound());
    }

    @Test(expected = NotUniqueFoundException.class)
    public void testNotUniqueFound() throws Exception {
        when(query.list()).thenReturn(Lists.newArrayList(dummyEntity, dummyEntity));
        table.find();
    }

    @Test
    public void testList() throws Exception {
        List<DummyEntity> itens = table.list();
        assertEquals(1, itens.size());
    }

    @Test
    public void testLink() throws Exception {
        LinkedList<DummyEntity> itens = table.link();
        assertEquals(1, itens.size());
    }

    @Test
    public void testSortedSet() throws Exception {
        SortedSet<DummyEntity> itens = table.sortedSet();
        assertEquals(1, itens.size());
    }

    @Test
    public void testMap() throws Exception {
        Map<String, DummyEntity> map = table.map("id.value");
        assertTrue(map.containsKey(Identity.ZERO.getValue()));
    }

    @Test
    public void testMultimap() throws Exception {
        Multimap<Object, Object> multimap = table.multMap("id");
        assertTrue(multimap.containsKey(Identity.ZERO));
    }

    @Test
    public void testEntityQuery() throws Exception {
        String query = entity.query();
        assertEquals("FROM DummyEntity dummyEntity", query);
    }

    @Test
    public void testEntityEqual() throws Exception {
        String query = entity.name("Tom").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name = :name$Tom", query);
    }

    @Test
    public void testEntityLike() throws Exception {
        String query = entity.document("321935788").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.document LIKE :document$%321935788%", query);
    }

    @Test
    public void testEntityAnd() throws Exception {
            String query = entity.name("Tom").idade(30).query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name = :name$Tom AND dummyEntity.idade = :idade$30", query);
    }

    @Test
    public void testEntityOR() throws Exception {
        String query = entity.name("Tom").or().name("Ridley").query();
        assertEquals("FROM DummyEntity dummyEntity WHERE dummyEntity.name = :name$Tom OR dummyEntity.name = :name$Ridley", query);
    }

    @Test
    public void testMaxResult() throws Exception {
        String query = table.max(1000).query();
    }

}
