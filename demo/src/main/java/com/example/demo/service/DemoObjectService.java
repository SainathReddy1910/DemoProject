package com.example.demo.service;

import com.example.demo.entity.DemoObject;

public interface DemoObjectService {
    DemoObject createObject(int version, String name);
}
