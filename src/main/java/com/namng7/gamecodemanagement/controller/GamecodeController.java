package com.namng7.gamecodemanagement.controller;

import com.namng7.gamecodemanagement.obj.ProcessRecord;
import com.namng7.gamecodemanagement.service.GamecodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/datn/gamecode")
public class GamecodeController {
    @Autowired
    private GamecodeService gamecodeService;

    @PostMapping("/generateGamecode")
    public ResponseEntity<?> generateGamecode(@RequestBody ProcessRecord record){
//        ProcessRecord record = new ProcessRecord();
//        record.setAmount(amount);
        try {
            gamecodeService.generateGamecode(record);
            return ResponseEntity.ok(record);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(record);
        }
    }

}
