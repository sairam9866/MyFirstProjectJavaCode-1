package com.fedex.ziptodest.repository.iseries.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fedex.ziptodest.model.CountryCode;

@Repository("countryCodeRedisRepository")
public interface CountryCodeRedisRepository extends CrudRepository<CountryCode, String> {
}
