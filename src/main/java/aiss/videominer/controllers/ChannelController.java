package aiss.videominer.controllers;
import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/channels")
public class ChannelController {

    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelController(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }

    @GetMapping
    public List<Channel> findAll(){
        return channelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Channel findOne(@PathVariable String id){
        Optional<Channel> channel = channelRepository.findById(id);
        if (channel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El canal no existe");
        }

        return channel.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Channel createChannel(@RequestBody Channel channel){
        return channelRepository.save(channel);
    }

}
