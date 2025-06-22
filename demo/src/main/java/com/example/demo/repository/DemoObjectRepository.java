package com.example.demo.repository;

import com.example.demo.entity.DemoObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoObjectRepository extends JpaRepository<DemoObject,Integer> {
}
