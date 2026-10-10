package com.taxistore.dao.impl;

import com.taxistore.dto.SituationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SituationDTOBuilder {

    /**
     * 從 ResultSet 轉換成 SituationDTO
     * 注意：不會處理 receipts（因為 receipts 是在 while 迴圈裡面另外塞的）
     */
    public static SituationDTO fromResultSet(ResultSet rs) throws SQLException {
        // 時間格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        Timestamp createDateTs = rs.getTimestamp("createDate");
        Timestamp giveCarDateTs = rs.getTimestamp("giveCarDate");
        Timestamp terminateDateTs = rs.getTimestamp("terminateDate");

        String createDate = createDateTs != null ? dateFormat.format(createDateTs) : null;
        String createTime = createDateTs != null ? timeFormat.format(createDateTs) : null;
        String giveCarDate = giveCarDateTs != null ? dateFormat.format(giveCarDateTs) : null;
        String terminateDate = terminateDateTs != null ? dateFormat.format(terminateDateTs) : null;

        return new SituationDTO(
                rs.getLong("situationId"),
                rs.getLong("carseq"),
                rs.getString("carno"),
                rs.getLong("driverseq"),
                rs.getString("driverName"),
                rs.getString("driverId"),
                rs.getString("portable"),
                rs.getString("specialCode"),
                rs.getString("companyName"),
                rs.getLong("companyseq"),
                createDate,
                createTime,
                giveCarDate,
                terminateDate,
                rs.getString("status"),
                rs.getInt("recvDeposit"),
                rs.getInt("retDeposit"),
                new ArrayList<>() // receipts 先給空的，外面再塞
        );
    }

    private SituationDTOBuilder() {
        // 工具類別，不要被 new 出來
    }
}
