package com.taxistore.dao.impl;

import com.taxistore.pojo.Fine;
import com.taxistore.pojo.Result;
import com.utils.Log;


public class FineDao extends AbstractFormDao<Fine> {
    private static final String TABLE_NAME = "Fine";
    private static final String KEY_NAME = "fineSeq";

    public FineDao() {
        super(Fine.class, TABLE_NAME,KEY_NAME);
    }


    public static void main(String[] args)
    {
        FineDao dao=new FineDao();

        Fine fine=new Fine();
        fine.setFineSeq(16885L);
        fine.setFineNo("AG1234566");

        Result<Integer> result=dao.updateEntityByNonNullFields(fine);
        Log.normal(result.toJSON());
    }
}