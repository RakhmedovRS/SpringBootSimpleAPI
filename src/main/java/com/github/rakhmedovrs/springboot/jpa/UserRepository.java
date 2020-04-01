package com.github.rakhmedovrs.springboot.jpa;

import org.springframework.data.repository.CrudRepository;

/**
 * @author RakhmedovRS
 * @created 01-Apr-20
 */
public interface UserRepository extends CrudRepository<User, Long>
{
}
