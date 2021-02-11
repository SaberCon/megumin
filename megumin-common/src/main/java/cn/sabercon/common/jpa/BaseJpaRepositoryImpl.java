package cn.sabercon.common.jpa;

import cn.sabercon.common.anno.Tx;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.util.MethodInvocationRecorder;

import javax.persistence.EntityManager;
import java.util.function.Function;

/**
 * @author SaberCon
 * @since 1.0.0
 */
public class BaseJpaRepositoryImpl<T> extends SimpleJpaRepository<T, Long> implements BaseJpaRepository<T> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    private final PersistenceProvider provider;

    /**
     * 参考了官方示例的实现
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
        this.provider = PersistenceProvider.fromEntityManager(entityManager);
    }

    @Tx
    @Override
    public int inc(Long id, Function<T, ?> property, Integer inc) {
        var prop = getPropName(property);
        var cb = em.getCriteriaBuilder();
        var update = cb.createCriteriaUpdate(entityInformation.getJavaType());
        var root = update.from(entityInformation.getJavaType());
        update.set(root.<Number>get(prop), cb.sum(root.get(prop), inc))
                .where(cb.equal(root.get(entityInformation.getIdAttribute()), id));
        return em.createQuery(update).executeUpdate();
    }

    @Tx
    @Override
    public boolean addRelation(T entity, boolean undo) {
        var relationOpt = findOne(Example.of(entity));
        if (relationOpt.isPresent() && undo) {
            delete(relationOpt.get());
            return true;
        }
        if (relationOpt.isEmpty() && !undo) {
            save(entity);
            return true;
        }
        return false;
    }

    @Override
    public String getPropName(Function<T, ?> property) {
        return MethodInvocationRecorder.forProxyOf(entityInformation.getJavaType()).record(property).getPropertyPath().orElseThrow();
    }

    @Override
    public Sort getSort(Function<T, ?> property) {
        return Sort.sort(entityInformation.getJavaType()).by(property);
    }
}
