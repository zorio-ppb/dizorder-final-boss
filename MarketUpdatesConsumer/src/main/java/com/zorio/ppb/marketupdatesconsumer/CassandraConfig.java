package com.zorio.ppb.marketupdatesconsumer;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${spring.cassandra.local-datacenter}")
    private String localDatacenter;

    @Bean
    public CqlSessionFactoryBean cassandraSession() {
        CqlSessionFactoryBean session = new CqlSessionFactoryBean();
        session.setContactPoints(contactPoints);
        session.setKeyspaceName(keyspace);
        session.setLocalDatacenter(localDatacenter);
        session.setKeyspaceCreations(this.getKeyspaceCreations());

        // uncomment if you want to delete the keyspace on shutdown
        // session.setKeyspaceDrops(this.getKeyspaceDrops());

        return session;
    }

    @Bean
    public CassandraTemplate cassandraTemplate(CqlSession cassandraSession) {
        return new CassandraTemplate(cassandraSession);
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

        CreateKeyspaceSpecification keyspaceSpecification = CreateKeyspaceSpecification.createKeyspace(keyspace)
                                                                                       .withSimpleReplication()
                                                                                       .with(KeyspaceOption.DURABLE_WRITES, true)
                                                                                       .ifNotExists();

        return List.of(keyspaceSpecification);

    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {

        DropKeyspaceSpecification keyspaceSpecification = DropKeyspaceSpecification.dropKeyspace(keyspace).ifExists();
        return List.of(keyspaceSpecification);

    }

    @Override
    public String getContactPoints() { return contactPoints; }

    @Override
    public String getKeyspaceName() { return keyspace; }

    @Override
    public SchemaAction getSchemaAction() { return SchemaAction.CREATE_IF_NOT_EXISTS; }

    @Override
    public String[] getEntityBasePackages() { return new String[] { "com.zorio.ppb.marketupdatesconsumer.entities" }; }


}
