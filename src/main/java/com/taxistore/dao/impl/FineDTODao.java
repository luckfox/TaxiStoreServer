package com.taxistore.dao.impl;


import com.taxistore.pojo.Fine;
import com.taxistore.dto.FineDTO;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;

public class FineDTODao extends AbstractFormDao<FineDTO>
{
    private static final String TABLE_NAME = "Fine";
    private static final String KEY_NAME = "fineSeq";

    private static final String sql = """
              SELECT 
                    f.fineSeq as fineSeq,
                    f.createdate as createdate,
                    f.fineNo as fineNo,
                    f.tactName as tactName,
                    f.fineDate as fineDate,
                    f.fineHH as fineHH,
                    f.fineNN as fineNN,
                    f.situationId as situationId,
                    f.fineRuleId as fineRuleId,
                    fr.rule1 as rule1,
                    fr.rule2 as rule2,
                    fr.rule3 as rule3,
                    fr.description as description,
                    f.fineContact as fineContact,
                    f.fineCreate as fineCreate,
                    f.fineClDate as fineClDate,
                    f.amount as amount,
                    f.fineAdd as fineAdd,
                    f.fineText1 as fineText1,
                    f.fineText2 as fineText2,
                    f.passDate as passDate,
                    f.fineStatus as fineStatus,
                    f.payDate as payDate,
                    f.payStatus as payStatus,
                    f.remark as remark,
                    c.carseq AS carseq,
                    c.carno AS carno,
                    d.driverseq AS driverseq,
                    d.drivername AS driverName           
                FROM fine f
                LEFT JOIN fine_rule fr ON fr.fineRuleSeq = f.fineRuleId
                LEFT JOIN situation s ON f.situationId = s.id
                LEFT JOIN driver d ON s.driverseq = d.driverseq
                LEFT JOIN car c ON c.carseq = s.carseq                  
            """;
    private static final String EquCondition=" WHERE f.fineSeq= ? ;";
    private static final String FineNoEquCondition=" WHERE f.fineNo= ? ;";
    private static final String FineNoLikeCondition=" WHERE f.fineNo LIKE CONCAT(?, '%')";
    private static final String DecCondition=" WHERE f.fineSeq < ? ORDER BY f.fineSeq DESC limit 1;";
    private static final String IncCondition=" WHERE f.fineSeq > ? ORDER BY f.fineSeq ASC limit 1;";
    private static final String FinalCondition="ORDER BY f.fineSeq DESC LIMIT 0 , 1;";
    private static final String FirstCondition="ORDER BY f.fineSeq LIMIT 1;";

    public FineDTODao() {
        super(FineDTO.class, TABLE_NAME,KEY_NAME);
    }

    public Result<FineDTO>  getFineDTO(Long serialNo)
    {
        return queryForOne(FineDTO.class,sql+EquCondition,serialNo);
    }
    public Result<FineDTO> getLastFineDTO()
    {
        return queryForOne(FineDTO.class,sql+FinalCondition);
    }
    public Result<FineDTO> getFirstFineDTO()
    {
        return queryForOne(FineDTO.class,sql+FirstCondition);
    }
    public Result<FineDTO> getPreviousFineDTO(Long serialNo)
    {
        return queryForOne(FineDTO.class,sql+DecCondition,serialNo);
    }
    public Result<FineDTO> getNextFineDTO(Long serialNo)
    {
        return queryForOne(FineDTO.class,sql+IncCondition,serialNo);
    }

    public Result<List<FineDTO>> getFinesByCarNo(String carno)
    {
        String condition=" WHERE c.carno= ? ;";
        Result<List<FineDTO>>result= queryForList(FineDTO.class,sql+condition,carno);
        return result;
    }
    public Result<FineDTO> getFineByFineNo(String fineNo)
    {

        Result<FineDTO>result= queryForOne(FineDTO.class,sql+FineNoEquCondition,fineNo);
        return result;
    }
    public Result<List<FineDTO>> getFineDTOByFineNoLike(String fineNoPart) {
        Result<List<FineDTO>>result= queryForList(FineDTO.class,sql+FineNoLikeCondition,fineNoPart);
        return result;
    }

    public static void main(String[] args)
    {
        FineDTODao dao=new FineDTODao();
        Result<FineDTO> result0=dao.getFirstEntity();
        Log.debug(result0.toJSON());
        Result<List<FineDTO>>result=dao.getFinesByCarNo("TDX-6519");
        Log.debug(result.toJSON());
        Fine fine=new Fine();
        fine.setFineSeq(16885L);
        fine.setFineNo("AG1234566");

        //dao.updateEntityByNonNullFields(fine);
    }
}
