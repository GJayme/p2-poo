package ifsp.domain.usercases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO <T, K>{
    K create(T type);

    Optional<T> readOne(K key);

    List<T> readAll();

    boolean update(T type);

    boolean delete(T type);

    boolean deleteByKey(K key);

}
