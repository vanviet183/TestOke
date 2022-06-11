package com.hit.product.applications.services.impl;

import com.hit.product.adapter.web.v1.transfer.responses.TrueFalseResponse;
import com.hit.product.applications.repositories.HelpRepository;
import com.hit.product.applications.services.HelpService;
import com.hit.product.configs.exceptions.NotFoundException;
import com.hit.product.domains.dtos.HelpDto;
import com.hit.product.domains.entities.Help;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpServiceImpl implements HelpService {

    @Autowired
    HelpRepository helpRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Help> getHelps() {
        return helpRepository.findAll();
    }

    @Override
    public Help getHelpById(Long id) {
        Optional<Help> help = helpRepository.findById(id);
        checkHelpException(help);
        return help.get();
    }

    @Override
    public Help createHelp(HelpDto helpDto) {
        return createOrUpdate(new Help(), helpDto);
    }

    @Override
    public Help updateHelp(Long id, HelpDto helpDto) {
        Optional<Help> help = helpRepository.findById(id);
        checkHelpException(help);
        return createOrUpdate(help.get(), helpDto);
    }

    private Help createOrUpdate(Help help, HelpDto helpDto) {
        modelMapper.map(helpDto, help);
        return helpRepository.save(help);
    }

    @Override
    public TrueFalseResponse deleteHelp(Long id) {
        Optional<Help> help = helpRepository.findById(id);
        checkHelpException(help);
        helpRepository.deleteById(id);
        return new TrueFalseResponse(true);
    }

    private void checkHelpException(Optional<Help> help) {
        if(help.isEmpty()) {
            throw new NotFoundException("Not Found");
        }
    }
}
