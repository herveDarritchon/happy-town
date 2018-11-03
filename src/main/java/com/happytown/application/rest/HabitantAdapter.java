package com.happytown.application.rest;

import com.happytown.domain.entities.Habitant;
import com.happytown.domain.use_cases.GetAllHabitants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/habitants")
@Api(description = "API de gestion des habitants de Happy Town")
public class HabitantAdapter {

    private final GetAllHabitants getAllHabitants;

    public HabitantAdapter(GetAllHabitants getAllHabitants) {
        this.getAllHabitants = getAllHabitants;
    }

    @GetMapping
    @ApiOperation("Retourne la liste des habitants de Happy Town")
    public List<HabitantApi> getAllHabitants() {
        return getAllHabitants.execute()
                .stream()
                .map(this::toHabitantApi)
                .collect(toList());
    }

    private HabitantApi toHabitantApi(Habitant habitant) {
        HabitantApi habitantApi = new HabitantApi();
        habitantApi.setId(habitant.getId());
        habitantApi.setNom(habitant.getNom());
        habitantApi.setPrenom(habitant.getPrenom());
        habitantApi.setEmail(habitant.getEmail());
        habitantApi.setDateNaissance(habitant.getDateNaissance());
        habitantApi.setDateArriveeCommune(habitant.getDateArriveeCommune());
        habitantApi.setAdressePostale(habitant.getAdressePostale());
        habitantApi.setCadeauOffert(habitant.getCadeauOffert());
        habitantApi.setDateAttributionCadeau(habitant.getDateAttributionCadeau());
        return habitantApi;
    }

}