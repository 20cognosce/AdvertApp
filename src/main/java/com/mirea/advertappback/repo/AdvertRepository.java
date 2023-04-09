package com.mirea.advertappback.repo;

import com.mirea.advertappback.domain.entity.Advert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Integer> {

}
