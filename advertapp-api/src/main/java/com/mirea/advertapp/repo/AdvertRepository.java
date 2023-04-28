package com.mirea.advertapp.repo;

import com.mirea.advertapp.domain.entity.Advert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {

    List<Advert> findByTitleContainsIgnoreCase(String title);
}
