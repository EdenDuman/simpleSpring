package simpe.spring.repository;

import java.util.List;
import java.util.Optional;

/**
 * @param <T> Entity (Table)
 * @param <K> PK
 */
public interface SimpleSpringRepository<T, K> {
    List<T> findAll();

    Optional<T> findById(K id);

    void save(T t);
}
