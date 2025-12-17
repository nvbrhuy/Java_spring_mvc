package vn.hoidanit.laptopshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;

//crud: create, read, upadate, delete
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User save(User hoidanit);
}
