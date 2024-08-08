package com.kalanso.event.Repository;

import com.kalanso.event.Model.CategorieBillet;
import com.kalanso.event.Model.CategorieEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieBilletRepo extends JpaRepository<CategorieBillet, Long> {
    CategorieBillet findByCategory( String Category);

}
