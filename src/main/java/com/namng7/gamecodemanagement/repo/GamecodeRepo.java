package com.namng7.gamecodemanagement.repo;

import com.namng7.gamecodemanagement.model.GamecodeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamecodeRepo extends JpaRepository<GamecodeDetail, Long> {
}
