package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.modelos.TablaAmortizacion;

public interface TablaAmortizacionRepository extends JpaRepository<TablaAmortizacion,Integer> {
    
}
