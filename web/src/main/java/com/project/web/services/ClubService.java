package com.project.web.services;

import com.project.web.dto.ClubDto;
import com.project.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(Club club);
}
