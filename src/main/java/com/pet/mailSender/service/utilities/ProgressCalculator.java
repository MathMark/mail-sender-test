package com.pet.mailSender.service.utilities;

import org.springframework.stereotype.Service;

@Service
public class ProgressCalculator {
    public int getProgress(int size, int progress){
        return (progress * 100)/size;
    }
}
