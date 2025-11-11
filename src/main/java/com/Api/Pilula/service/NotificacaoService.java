package com.Api.Pilula.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.Pilula.dtos.DoseInfoDto;
import com.Api.Pilula.model.Dose;
import com.Api.Pilula.repository.DoseRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class NotificacaoService {

    @Autowired
    private DoseRepository doseRepository;

    @Autowired
    private JwtService jwtService;

    public List<DoseInfoDto> verificarDosesNoPeriodo(LocalDateTime inicio, LocalDateTime fim, HttpServletRequest request) {
        String usuarioCpf = jwtService.getSubjectFromRequest(request);

        List<Dose> doses = doseRepository.buscarDosesPorCpfEPeriodo(usuarioCpf, inicio, fim);

        List<DoseInfoDto> resultado = new ArrayList<>();
        for (Dose dose : doses) {
            resultado.add(new DoseInfoDto(
                dose.id(),
                dose.medicamento().id(),
                dose.horaPrevista(),
                dose.status()
            ));
        }

        return resultado;
    }

}
