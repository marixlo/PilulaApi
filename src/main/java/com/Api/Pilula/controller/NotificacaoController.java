package com.Api.Pilula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import com.Api.Pilula.dtos.DoseInfoDto;
import com.Api.Pilula.service.NotificacaoService;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/{medicamentoId}")
    public ResponseEntity<List<DoseInfoDto>> listarNotificacoes(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim,
            HttpServletRequest request) {

        List<DoseInfoDto> notificacoes = notificacaoService.verificarDosesNoPeriodo(inicio, fim, request);
        return ResponseEntity.ok(notificacoes);
    }
}
