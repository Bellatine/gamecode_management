package com.namng7.gamecodemanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "gamecode_detail")
public class GamecodeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gamecode;

    @Column(nullable = false)
    private String serial;

    @Column(nullable = false)
    private Date start_date;

    @Column
    private Date valid_date;

    @Column(nullable = false)
    private Date create_date;

    @Column(nullable = false)
    private Integer status;

    @PrePersist
    public void generateSerial() {
        // Chỉ tính serial khi đối tượng đã có id
        if (this.id != null) {
            this.serial = String.format("%012d", this.id);
        }
    }

}