package backRealSit2.backRealSit2.controller;


import backRealSit2.backRealSit2.entity.Reporte;
import backRealSit2.backRealSit2.service.ReporteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/backRealsit2/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public  ReporteController(ReporteService reporteService){
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<Reporte> listarReporte(){
        return  reporteService.obtenerReportes();
    }


}
