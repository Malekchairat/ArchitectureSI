package tn.esprit.ds.championnat.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championnat.entities.Equipe;
import tn.esprit.ds.championnat.services.IEquipeService;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/equipe")
@Tag(name = "Equipe Management",
        description = "APIs for managing teams in the championship system")
public class EquipeController {

    private final IEquipeService equipeService;

    public EquipeController(IEquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @Operation(
            summary = "Add a new team",
            description = "Create and save a new team in the system"
    )
    @ApiResponse(responseCode = "200",
            description = "Team successfully created",
            content = @Content(schema = @Schema(implementation = Equipe.class)))
    @PostMapping("/add")
    public Equipe addEquipe(@RequestBody Equipe equipe) {
        return equipeService.ajouterEquipe(equipe);
    }


    // ========================= GET ALL =========================

    @Operation(
            summary = "Get all teams",
            description = "Retrieve the list of all teams registered in the system"
    )
    @ApiResponse(responseCode = "200",
            description = "List of teams retrieved successfully",
            content = @Content(schema = @Schema(implementation = Equipe.class)))
    @GetMapping("/all")
    public List<Equipe> getAllEquipes() {
        return equipeService.getAllEquipes();
    }


    // ========================= GET BY ID =========================

    @Operation(
            summary = "Get team by ID",
            description = "Retrieve a specific team using its unique identifier"
    )
    @ApiResponse(responseCode = "200",
            description = "Team found",
            content = @Content(schema = @Schema(implementation = Equipe.class)))
    @ApiResponse(responseCode = "404",
            description = "Team not found")
    @GetMapping("/{id}")
    public ResponseEntity<Equipe> getEquipeById(@PathVariable Long id) {

        return equipeService.getEquipeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // ========================= DELETE =========================

    @Operation(
            summary = "Delete a team",
            description = "Delete a team using its unique identifier"
    )
    @ApiResponse(responseCode = "200",
            description = "Team deleted successfully")
    @ApiResponse(responseCode = "404",
            description = "Team not found")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEquipe(@PathVariable Long id) {

        if (equipeService.getEquipeById(id).isPresent()) {
            equipeService.deleteEquipe(id);
            return ResponseEntity.ok("Team deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}