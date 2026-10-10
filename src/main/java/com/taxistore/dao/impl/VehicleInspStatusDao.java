package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.taxistore.dto.VehicleInspStatusDTO;
import com.taxistore.pojo.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleInspStatusDao {
    public Result<List<VehicleInspStatusDTO>> fetchVehicleInspectionStatusByCarNo(String carNo) {
        String sql = "SELECT carno, driverSeq, driverName, caryear, companyName, shingleDat, terminateDate, newRegistration, terminatedStatus " +
                "FROM vehicle_inspection_status_table WHERE carno = ?";

        List<VehicleInspStatusDTO> resultList = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, carNo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VehicleInspStatusDTO dto = new VehicleInspStatusDTO();

                    dto.setCarno(rs.getString("carno"));
                    dto.setDriverSeq(rs.getString("driverSeq"));
                    dto.setDriverName(rs.getString("driverName"));
                    dto.setCaryear(rs.getTimestamp("caryear"));
                    dto.setCompanyName(rs.getString("companyName"));
                    dto.setShingleDat(rs.getTimestamp("shingleDat"));
                    dto.setTerminateDate(rs.getTimestamp("terminateDate"));
                    dto.setNewRegistration(rs.getTimestamp("newRegistration"));
                    dto.setTerminatedStatus(rs.getTimestamp("terminatedStatus"));

                    resultList.add(dto);
                }
            }

            return Result.success(resultList);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("fetchVehicleInspectionStatusByCarNo.error: " + e.getMessage());
        }
    }
}
