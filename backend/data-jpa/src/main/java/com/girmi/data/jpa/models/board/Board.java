package com.girmi.data.jpa.models.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BOARD")
public class Board {
    @Id
    @Column(name = "BRD_IDX", unique = true)
    @Schema(name = "brdIdx", description = "brdIdx")
    private Integer brdIdx;

    @Column(name = "BRD_TYPE", nullable = false)
    @Schema(name = "brdType", description = "board type")
    private String brdType;

    @Column(name = "BRD_CONTENT", nullable = false)
    @Schema(name = "brdContent", description = "Board Content")
    private String brdContent;

    @Column(name = "BRD_TITLE")
    @Schema(name = "brdTitle", description = "board type")
    private String brdTitle;

    @Column(name = "BRD_IMG_URL")
    @Schema(name = "brdImgUrl", description = "board image url")
    private String brdImgUrl;

    @Column(name = "BRD_IMG_TEXT")
    @Schema(name = "brdImgText", description = "board image text")
    private String brdImgText;

    @Column(name = "BRD_LINK_TEXT")
    @Schema(name = "brdLinkText", description = "board link text")
    private String brdLinkText;

    @Column(name = "USE_YN")
    @Schema(name = "useYn", description = "use yn", defaultValue = "Y")
    private String useYn;

}