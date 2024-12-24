package com.namng7.gamecodemanagement.service;

import com.namng7.gamecodemanagement.model.GamecodeDetail;
import com.namng7.gamecodemanagement.obj.ProcessRecord;
import com.namng7.gamecodemanagement.repo.GamecodeRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GamecodeServiceImpl implements GamecodeService {
    private static final Logger logger = LogManager.getLogger(GamecodeService.class);
    private static StringBuilder log = new StringBuilder();

    @Autowired
    private GamecodeRepo gamecodeRepo;

    @Autowired
    private AesEncryptionService aesEncryptionService;

    @Override
    public void generateGamecode(ProcessRecord record) {
        logger.info("Start....");
        try {
            List<GamecodeDetail> listGamecode = new ArrayList<>();
            if (record.getAmount() < 1) {
                record.setErrorCode(-1);
                logger.error("so luong < 0");
                return;
            }
            for (int i = 1; i <= record.getAmount(); i++) {
                GamecodeDetail gamecodeDetail = new GamecodeDetail();

                Calendar calendar = Calendar.getInstance();
                gamecodeDetail.setCreate_date(calendar.getTime());
                gamecodeDetail.setStart_date(calendar.getTime());
                calendar.add(Calendar.YEAR, 1);
                gamecodeDetail.setValid_date(calendar.getTime());
                gamecodeDetail.setStatus(0);
                GamecodeDetail savedGamecode = saveGamecode(gamecodeDetail);
                listGamecode.add(savedGamecode);
            }
            record.setListGamecode(listGamecode);
            logger.info("Tao gamecode thanh cong!");
            record.setErrorCode(0);
        } catch (Exception e) {
            logger.error("Loi khi tao gamecode", e);
        }
    }

    @Override
    public void getListUsed(ProcessRecord record) {

    }

    private GamecodeDetail saveGamecode(GamecodeDetail gamecodeDetail) throws Exception {
        boolean isSaved = false;
        GamecodeDetail savedGamecode = null;

        do {
            try {
                UUID uuid = UUID.randomUUID();

                // Chuyển UUID thành chuỗi Base64
                String base64Encoded = Base64.getUrlEncoder().encodeToString(uuid.toString().getBytes());

                // Lấy 12 ký tự đầu tiên
                String encryptGamecode = aesEncryptionService.encrypt(base64Encoded.substring(0, 12));
                gamecodeDetail.setGamecode(encryptGamecode);
                savedGamecode = gamecodeRepo.save(gamecodeDetail);
                savedGamecode.setSerial(String.format("%012d", savedGamecode.getId()));
                savedGamecode = gamecodeRepo.save(savedGamecode);
                isSaved = true;
                logger.info("Gamecode " + aesEncryptionService.decrypt(encryptGamecode) + " ma hoa: " + encryptGamecode);
            } catch (DataIntegrityViolationException e) {
                logger.error("Gamecode da ton tai! " + e.getMessage());
            }
        } while (!isSaved);

        return savedGamecode;
    }
}
