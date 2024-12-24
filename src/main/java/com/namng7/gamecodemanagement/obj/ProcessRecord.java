package com.namng7.gamecodemanagement.obj;

import com.namng7.gamecodemanagement.model.GamecodeDetail;
import lombok.Data;

import java.util.List;

@Data
public class ProcessRecord {
    private int amount;
    private List<GamecodeDetail> listGamecode;
    private Integer errorCode;
}
