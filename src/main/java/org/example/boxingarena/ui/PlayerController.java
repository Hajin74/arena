package org.example.boxingarena.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.dto.PlayerJoinRequest;
import org.example.boxingarena.service.PlayerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/join")
    public String join(@RequestBody PlayerJoinRequest request) {
        log.info("join - {}", request.toString());
        playerService.join(request);

        return "join success!!";
    }

}
