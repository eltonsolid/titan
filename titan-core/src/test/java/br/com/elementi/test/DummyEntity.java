package br.com.elementi.test;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.elementi.core.annotation.QSearchLike;
import br.com.elementi.core.domain.DomainEntity;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.qtable.QEntity;
import br.com.elementi.core.qtable.QSearch;
import br.com.elementi.core.tools.BuilderEntity.BuilderTemplate;

@Entity
public class DummyEntity extends DomainEntity implements QSearch {

    @Id
    public DummyIdentityEntity id;
    public int opart;
    private static String stam = "";
    private int idade;
    @Column(name = "DOC")
    private Integer document;
    @Column
    private String name;
    private List<String> codes;
    private Set<String> keys;
    private Map<String, String> combos;
    private DummyEntityType type;
    private Date create;

    public DummyEntity() {

    }

    public interface Entity extends QEntity<Entity> {

        Entity name(String name);

        @QSearchLike
        Entity document(String name);


        Entity idade(Integer idade);

    }

    public static Entity create(Identity identity) throws Exception {
        return QEntity.of(Entity.class, DummyEntity.class).id(identity);
    }


    public interface Builder extends BuilderTemplate<DummyEntity> {

        public Builder idade(Integer idade);

        public Builder name(String name);

        public Builder type(DummyEntityType type);

        public Builder create(Date date);

        public String document(String document);

    }

    @Override
    public Class<? extends DomainEntity> from() throws Exception {
        return this.getClass();
    }

    @Override
    public Identity getId() {
        return id;
    }

    public int getIdade() {
        return idade;
    }

    public Integer getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public DummyEntityType getType() {
        return type;
    }

    public List<String> getCodes() {
        return this.codes;
    }

    public Set<String> getKeys() {
        return this.keys;
    }

    public Map<String, String> getCombos() {
        return this.combos;
    }

}
