package com.gestion.hospitaliere.dao;


import com.gestion.hospitaliere.entity.RendezVousStatus;
import com.gestion.hospitaliere.entity.Rendezvous;

import java.util.List;

public interface RendezVousDao extends JpaRepository<Rendezvous>{
    List<Rendezvous> findByPatient(Long id);
    List<Rendezvous> findByDoctor(Long id, RendezVousStatus status);
    List<Rendezvous> findByStatus(RendezVousStatus status);
}
