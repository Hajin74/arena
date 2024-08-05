package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.auth.CustomUserDetails;
import org.example.boxingarena.domain.Application;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.domain.TournamentStatus;
import org.example.boxingarena.domain.User;
import org.example.boxingarena.dto.application.ApplicationFormRequest;
import org.example.boxingarena.dto.application.DetailApplicationResponse;
import org.example.boxingarena.dto.application.MyApplicationListResponse;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.ApplicationRepository;
import org.example.boxingarena.repository.TournamentRepository;
import org.example.boxingarena.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public void applyForTournament(CustomUserDetails customUserDetails, ApplicationFormRequest request) {
        log.info("applyForTournament - service");

        User player = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.PLAYER_NOT_FOUND));

        Tournament tournament = tournamentRepository.findById(request.getTournamentId())
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        if (!tournament.getStatus().equals(TournamentStatus.APPLICATION_OPENING)) {
            throw new CustomException(ErrorCode.APPLICATION_FOR_TOURNAMENT_PERIOD_CLOSED);
        }

        Application newApplication = new Application(request.getTournamentId(), player.getId());
        applicationRepository.save(newApplication);
    }

    @Transactional
    public void approveApplication(CustomUserDetails customUserDetails, Long applicationId) {
        log.info("approveApplication - service");

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));

        Tournament tournament = tournamentRepository.findById(application.getTournamentId())
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        if (tournament != null) {
            if (!tournament.getOrganizerId().equals(organizer.getId())) {
                throw new CustomException(ErrorCode.ORGANIZER_ONLY_ALLOWED);
            }
        }

        application.approveApplication();
    }

    @Transactional
    public void rejectApplication(CustomUserDetails customUserDetails, Long applicationId) {
        log.info("rejectApplication - service");

        User organizer = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ORGANIZER_NOT_FOUND));

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));

        Tournament tournament = tournamentRepository.findById(application.getTournamentId())
                .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));

        if (tournament != null) {
            if (!tournament.getOrganizerId().equals(organizer.getId())) {
                throw new CustomException(ErrorCode.ORGANIZER_ONLY_ALLOWED);
            }
        }

        application.rejectApplication();
    }

    @Transactional
    public void cancelApplication(Long playerId, Long applicationId) {
        log.info("cancelApplication - service");

        if (!userRepository.existsById(playerId)) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        Optional<Application> application = applicationRepository.findById(applicationId);
        if (application.isEmpty()) {
            throw new CustomException(ErrorCode.APPLICATION_NOT_FOUND);
        }

        if (!application.get().getPlayerId().equals(playerId)) {
            throw new CustomException(ErrorCode.PLAYER_ONLY_ALLOWED);
        }

        application.get().cancelApplication();

    }

    @Transactional(readOnly = true)
    public DetailApplicationResponse getApplication(Long applicationId) {
        log.info("getApplication - service");

        Optional<Application> application = applicationRepository.findById(applicationId);
        if (application.isEmpty()) {
            throw new CustomException(ErrorCode.APPLICATION_NOT_FOUND);
        }

        return DetailApplicationResponse.from(application.get());
    }

    @Transactional(readOnly = true)
    public List<MyApplicationListResponse> getApplicationsByPlayer(CustomUserDetails customUserDetails) {
        log.info("getMyApplication - service");

        User player = userRepository.findByEmail(customUserDetails.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.PLAYER_NOT_FOUND));

        List<MyApplicationListResponse> myApplicationListResponses = new ArrayList<>();
        List<Application> applications = applicationRepository.findAllByPlayerId(player.getId());
        for (Application application : applications) {
            Tournament tournament = tournamentRepository.findById(application.getTournamentId())
                    .orElseThrow(() -> new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND));
            myApplicationListResponses.add(MyApplicationListResponse.from(application, tournament.getName(), player.getName()));
        }

        return myApplicationListResponses;
    }

    @Transactional(readOnly = true)
    public List<DetailApplicationResponse> getApplicationsForTournament(CustomUserDetails customUserDetails, Long tournamentId) {
        log.info("getApplicationsForTournament - service");

        if (!tournamentRepository.existsById(tournamentId)) {
            throw new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND);
        }

        List<DetailApplicationResponse> applicationResponses = new ArrayList<>();
        List<Application> applications = applicationRepository.findAllByTournamentId(tournamentId);
        for (Application application : applications) {
            User player = userRepository.findById(application.getPlayerId())
                    .orElseThrow(() -> new CustomException(ErrorCode.PLAYER_NOT_FOUND));
            applicationResponses.add(DetailApplicationResponse.from(application, player.getName()));
        }

        return applicationResponses;
    }

}

