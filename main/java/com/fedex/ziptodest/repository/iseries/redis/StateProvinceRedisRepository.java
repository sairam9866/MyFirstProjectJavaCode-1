package com.fedex.ziptodest.repository.iseries.redis;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fedex.ziptodest.model.StateProvince;


@Repository("stateProvinceRedisRepository")
public interface StateProvinceRedisRepository extends CrudRepository<StateProvince, String> {

	public List<StateProvince> selectByCountryCode(String... countryCodes);

	public List<StateProvince> findByCntryc(String countryCode);
}
