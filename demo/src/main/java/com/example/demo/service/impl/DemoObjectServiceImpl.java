package com.example.demo.service.impl;

import com.example.demo.entity.DemoObject;
import com.example.demo.repository.DemoObjectRepository;
import com.example.demo.service.DemoObjectService;
import org.springframework.stereotype.Component;

@Component
public class DemoObjectServiceImpl implements DemoObjectService {

    private final DemoObjectRepository demoObjectRepository;

    public DemoObjectServiceImpl(DemoObjectRepository demoObjectRepository) {
        this.demoObjectRepository = demoObjectRepository;
    }

    @Override
    public DemoObject createObject(int version, String name) {
        DemoObject demoObject = new DemoObject(version,name);
        demoObjectRepository.save(demoObject);
        return  demoObject;
    }
}
