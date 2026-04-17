package aiss.videominer.controllers;
import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/captions")
public class CaptionController {

    private final CaptionRepository captionRepository;

    @Autowired
    public CaptionController(CaptionRepository captionRepository){
        this.captionRepository = captionRepository;
    }

    @GetMapping
    public List<Caption> findAll(){
        return captionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Caption findOne(@PathVariable String id){
        Optional<Caption> Caption = captionRepository.findById(id);
        if (Caption.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La descripción no existe");
        }

        return Caption.get();
    }

}
