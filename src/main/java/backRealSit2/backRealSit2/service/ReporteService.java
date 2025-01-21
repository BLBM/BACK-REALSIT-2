package backRealSit2.backRealSit2.service;


import backRealSit2.backRealSit2.entity.Reporte;
import backRealSit2.backRealSit2.repository.ReporteRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    private final ReporteRepository reporteRepository;


    public ReporteService(ReporteRepository reporteRepository){
        this.reporteRepository = reporteRepository;
    }


    @Cacheable(value = "reportes")
    public List<Reporte> obtenerReportes(){return reporteRepository.findAll();}


    public Reporte obtenerReporteById(Long id){
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("reporte no encontrado "+id));
    }




}
