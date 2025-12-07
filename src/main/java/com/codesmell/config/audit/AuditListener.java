package com.codesmell.config.audit;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;

import java.security.Principal;
import java.time.Instant;
import java.util.Optional;

@ApplicationScoped
public class AuditListener implements PreInsertEventListener, PreUpdateEventListener {

    @Inject
    SecurityIdentity identity;

    @Inject
    EntityManagerFactory emf;

    void onStart(@Observes StartupEvent event) {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        assert registry != null;
        registry.getEventListenerGroup(org.hibernate.event.spi.EventType.PRE_INSERT).appendListener(this);
        registry.getEventListenerGroup(org.hibernate.event.spi.EventType.PRE_UPDATE).appendListener(this);
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        if (event.getEntity() instanceof Auditable auditable) {
            Instant now = Instant.now();
            Optional<Principal> user = Optional.of(identity.getPrincipal());
            String userName = user.map(Principal::getName).orElse("Anonymous");
            auditable.setCreatedDate(now);
            auditable.setCreatedBy(userName);
            auditable.setUpdatedDate(now);
            auditable.setUpdatedBy(userName);

            // Update state
            setValue(event.getPersister(), event.getState(), "createdDate", now);
            setValue(event.getPersister(), event.getState(), "createdBy", userName);
            setValue(event.getPersister(), event.getState(), "updatedDate", now);
            setValue(event.getPersister(), event.getState(), "updatedBy", userName);
        }
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        if (event.getEntity() instanceof Auditable auditable) {
            Instant now = Instant.now();
            Optional<Principal> user = Optional.of(identity.getPrincipal());
            String userName = user.map(Principal::getName).orElse("Anonymous");
            auditable.setUpdatedDate(now);
            auditable.setUpdatedBy(userName);

            // Update state
            setValue(event.getPersister(), event.getState(), "updatedDate", now);
            setValue(event.getPersister(), event.getState(), "updatedBy", userName);
        }
        return false;
    }

    private void setValue(EntityPersister persister, Object[] state, String propertyName, Object value) {
        int index = persister.getPropertyIndex(propertyName);
        state[index] = value;
    }
}
