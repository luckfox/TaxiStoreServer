package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.taxistore.dto.RecvEntryDTO;
import com.taxistore.pojo.RecvForm;

import com.taxistore.pojo.Result;
import com.utils.ResultSetMapper;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * `RecvFormDao` 負責與 `recvform` 表進行交互，包括新增、修改、刪除和查詢操作。
 */
public class RecvFormDao extends AbstractFormDao<RecvForm> {
    /** 對應的資料表名稱：car */
    private static final String TABLE_NAME = "RecvForm";
    /** 主鍵欄位名稱：carseq */
    private static final String KEY_NAME = "id";

    /** 額外加入的欄位，會在 insertEntity 時自動補值 */
    private static final Map<String, Supplier<Object>> extraFields = Map.of(
            "createdate",() -> new Timestamp(System.currentTimeMillis())
    );

    public RecvFormDao() {
        super(RecvForm.class, TABLE_NAME, KEY_NAME,extraFields);
    }

    public Object getExtraFieldValue(String fieldName)
    {
        // 檢查是否有對應的額外欄位，如果有則返回其值
        Supplier<Object> valueSupplier = extraFields.get(fieldName);
        return valueSupplier != null ? valueSupplier.get() : null;
    }


    public void ResultSetMapperTest()
    {
        String sql = """
            SELECT r.id,
                   r.formId,
                   f.code AS code,
                   f.accountCode AS accountCode,
                   r.summary,
                   r.debitCredit,
                   r.amount,
                   r.recvFormId,
                   r.entryOrder
            FROM recvEntry r
            LEFT JOIN fee f ON f.id = r.acc
            WHERE r.recvFormId = ?
            ORDER BY r.entryOrder ASC
        """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, 1L); // 替換為你想測試的 recvFormId

            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMapper<RecvEntryDTO> mapper = new ResultSetMapper<>();
                List<RecvEntryDTO> resultList = mapper.mapResultSetToList(rs, RecvEntryDTO.class);

                for (RecvEntryDTO dto : resultList) {
                    System.out.println(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        RecvFormDao dao = new RecvFormDao();

        dao.ResultSetMapperTest();
        // 測試插入
        // ==== 測試 insert ====
        RecvForm recvForm = new RecvForm();
        recvForm.setSituation(1894L);
        recvForm.setType("P");
        recvForm.setRemark("This is a test remark.");
        recvForm.setUserNo("U12345");
        recvForm.setUserName("John Doe");

        Result<Long> insertResult = dao.insertEntity(recvForm);
        if (!insertResult.isSuccess()) {
            System.out.println("Insert failed: " + insertResult.getErrorMessage());
            return;
        }
        Long newId = insertResult.getData();
        recvForm.setId(newId);
        System.out.println("Insert OK, id=" + newId);

        // ==== 測試 getEntity ====
        Result<RecvForm> fetched = dao.getEntity(newId);
        System.out.println("Fetched: " + fetched.getData());

        // ==== 測試 updateEntity ====
        recvForm.setRemark("Updated remark");
        dao.updateEntity(recvForm);

        // ==== 測試 updateEntityOnlyFields ====
        recvForm.setRemark("Remark from updateEntityOnlyFields");
        dao.updateEntityOnlyFields(recvForm, List.of("remark"));

        // ==== 測試 updateEntityExcludingFields ====
        recvForm.setRemark("Remark from updateEntityExcludingFields");
        dao.updateEntityExcludingFields(recvForm, List.of("type", "userNo"));

        // ==== 測試 existsByColumnValue ====
        Result<Boolean> exists = dao.existsByColumnValue("id", newId);
        System.out.println("Exists? " + exists.getData());

        // ==== 測試 getEntitiesByField ====
        Result<List<RecvForm>> listByField = dao.getEntitiesByField("situation", 1894L);
        System.out.println("List size (by field) = " + listByField.getData().size());

        // ==== 測試 getEntitiesByFields ====
        RecvForm criteria = new RecvForm();
        criteria.setSituation(1894L);
        Result<List<RecvForm>> listByFields = dao.getEntitiesByFields(criteria, List.of("situation"));
        System.out.println("List size (by fields) = " + listByFields.getData().size());

        // ==== 測試 getCountByColumns ====
        Result<Integer> count = dao.getCountByColumns(criteria, List.of("situation"));
        System.out.println("Count = " + count.getData());

        // ==== 測試 delete ====
        Result<Integer> deleteResult = dao.deleteEntity(newId);
        System.out.println("Delete rows: " + deleteResult.getData());

        System.out.println("All tests done!");

    }
}
