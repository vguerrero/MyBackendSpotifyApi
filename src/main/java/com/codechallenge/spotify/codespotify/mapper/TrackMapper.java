package com.codechallenge.spotify.codespotify.mapper;

import com.codechallenge.spotify.codespotify.dto.TrackDTO;
import com.codechallenge.spotify.codespotify.model.Track;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface TrackMapper {
    TrackDTO toDTO(Track entity);

    Track toEntity(TrackDTO dto);

}
