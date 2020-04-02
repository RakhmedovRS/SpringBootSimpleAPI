package com.github.rakhmedovrs.springboot.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author RakhmedovRS
 * @created 01-Apr-20
 */
@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long>
{
	List<User> findByRole(@Param("role") String role);
}
