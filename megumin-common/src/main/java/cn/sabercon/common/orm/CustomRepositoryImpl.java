package cn.sabercon.common.orm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public class CustomRepositoryImpl<T> implements CustomRepository<T> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void increment(Long id, String field) {
        // todo
    }
}
