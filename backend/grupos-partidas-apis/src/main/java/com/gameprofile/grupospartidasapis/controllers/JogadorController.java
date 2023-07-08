package com.gameprofile.grupospartidasapis.controllers;

import com.gameprofile.grupospartidasapis.entities.Jogador;
import com.gameprofile.grupospartidasapis.repositories.JogadorRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogadores")

public class JogadorController {
        private JogadorRepository repository;

        private RestTemplate restTemplate;
        public JogadorController(JogadorRepository repository, RestTemplate restTemplate){
            this.repository=repository;
            this.restTemplate=restTemplate;
        }

        @GetMapping
        public List<Jogador> findAll() {
            try {
                return repository.findAll();
            } catch(IllegalStateException e) {
                return Collections.emptyList();
            }
        }

        @GetMapping("/{id}")
        public Jogador findAll(@PathVariable Integer id) throws ObjectNotFoundException {
            Optional<Jogador> jogadorOptional = repository.findById(id);
            if(jogadorOptional.isPresent()) {
                return jogadorOptional.get();
            }else{
                throw new ObjectNotFoundException(id, "Jogador não encontrado com o ID " + id);

                }
            }

        
        @PostMapping
        public ResponseEntity<Jogador>salvar(@RequestBody Jogador jogador){
            return ResponseEntity.ok(repository.save(jogador));
        }
        
        
        @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
        public ResponseEntity<?> buscarJogadorPorNome(@PathVariable String summonerName) {
            String apiUrl = "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + "RGAPI-93297058-3a2f-4fc2-a962-c2542408f49b";
            ResponseEntity<?> response = restTemplate.getForEntity(apiUrl, Object.class);
            return response;


}
}