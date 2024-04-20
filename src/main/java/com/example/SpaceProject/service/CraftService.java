package com.example.SpaceProject.service;

import com.example.SpaceProject.entity.Craft;
import com.example.SpaceProject.entity.record.CraftRecord;
import com.example.SpaceProject.repository.CraftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CraftService {
    private final CraftRepository craftRepository;

   public Craft saveCraftToRepository(Craft craft){
       return craftRepository.save(craft);
    }
}
