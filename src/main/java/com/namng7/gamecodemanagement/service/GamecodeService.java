package com.namng7.gamecodemanagement.service;

import com.namng7.gamecodemanagement.model.GamecodeDetail;
import com.namng7.gamecodemanagement.obj.ProcessRecord;

import java.util.List;

public interface GamecodeService {
    void generateGamecode(ProcessRecord record);
    void getListUsed(ProcessRecord record);
}
