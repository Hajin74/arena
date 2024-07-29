package org.example.boxingarena.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boxingarena.domain.Application;
import org.example.boxingarena.domain.Tournament;
import org.example.boxingarena.domain.TournamentStatus;
import org.example.boxingarena.dto.ApplicationFormRequest;
import org.example.boxingarena.dto.DetailApplicationResponse;
import org.example.boxingarena.exception.CustomException;
import org.example.boxingarena.exception.ErrorCode;
import org.example.boxingarena.repository.ApplicationRepository;
import org.example.boxingarena.repository.TournamentRepository;
import org.example.boxingarena.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void applyForTournament(Long playerId, ApplicationFormRequest request) {
        log.info("applyForTournament - service");

        Optional<Tournament> tournament = tournamentRepository.findById(request.getTournamentId());
        if (tournament.isEmpty()) {
            throw new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND);
        }

        if (!userRepository.existsById(playerId)) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        if (!tournament.get().getStatus().equals(TournamentStatus.APPLICATION_OPENING)) {
            throw new CustomException(ErrorCode.APPLICATION_FOR_TOURNAMENT_PERIOD_CLOSED);
        }

        Application newApplication = new Application(request.getTournamentId(), playerId);
        applicationRepository.save(newApplication);
    }

    @Transactional
    public void approveApplication(Long organizerId, Long applicationId) {
        log.info("approveApplication - service");

        if (!userRepository.existsById(organizerId)) {
            throw new CustomException(ErrorCode.ORGANIZER_NOT_FOUND);
        }

        Optional<Application> application = applicationRepository.findById(applicationId);
        if (application.isEmpty()) {
            throw new CustomException(ErrorCode.APPLICATION_NOT_FOUND);
        }

        Optional<Tournament> tournament = tournamentRepository.findById(application.get().getTournamentId());
        if (tournament.isPresent()) {
            if (!tournament.get().getOrganizerId().equals(organizerId)) {
                throw new CustomException(ErrorCode.ORGANIZER_ONLY_ALLOWED);
            }
        }

        application.get().approveApplication();
    }

    @Transactional
    public void rejectApplication(Long organizerId, Long applicationId) {
        log.info("rejectApplication - service");

        if (!userRepository.existsById(organizerId)) {
            throw new CustomException(ErrorCode.ORGANIZER_NOT_FOUND);
        }

        Optional<Application> application = applicationRepository.findById(applicationId);
        if (application.isEmpty()) {
            throw new CustomException(ErrorCode.APPLICATION_NOT_FOUND);
        }

        Optional<Tournament> tournament = tournamentRepository.findById(application.get().getTournamentId());
        if (tournament.isPresent()) {
            if (!tournament.get().getOrganizerId().equals(organizerId)) {
                throw new CustomException(ErrorCode.ORGANIZER_ONLY_ALLOWED);
            }
        }

        application.get().rejectApplication();
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
    public List<DetailApplicationResponse> getApplicationsByPlayer(Long playerId) {
        log.info("getMyApplication - service");

        if (!userRepository.existsById(playerId)) {
            throw new CustomException(ErrorCode.PLAYER_NOT_FOUND);
        }

        return applicationRepository.findAllByPlayerId(playerId)
                .stream()
                .map(DetailApplicationResponse::from)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<DetailApplicationResponse> getApplicationsForTournament(Long organizerId, Long tournamentId) {
        log.info("getApplicationsForTournament - service");

        if (!userRepository.existsById(organizerId)) {
            throw new CustomException(ErrorCode.ORGANIZER_NOT_FOUND);
        }

        if (!tournamentRepository.existsById(tournamentId)) {
            throw new CustomException(ErrorCode.TOURNAMENT_NOT_FOUND);
        }

        return applicationRepository.findAllByTournamentId(tournamentId)
                .stream()
                .map(DetailApplicationResponse::from)
                .collect(Collectors.toList());
    }

}

