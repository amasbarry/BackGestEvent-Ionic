package com.kalanso.event.Repository;

import com.kalanso.event.Model.CategorieEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieEventRepo extends JpaRepository<CategorieEvent, Long>{
    CategorieEvent findByCategory( String Category);
}
