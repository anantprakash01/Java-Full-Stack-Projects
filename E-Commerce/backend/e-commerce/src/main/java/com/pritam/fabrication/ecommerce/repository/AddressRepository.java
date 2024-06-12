package com.pritam.fabrication.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pritam.fabrication.ecommerce.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
