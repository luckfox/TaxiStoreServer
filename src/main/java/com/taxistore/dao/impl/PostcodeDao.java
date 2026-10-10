package com.taxistore.dao.impl;

import com.taxistore.dto.PostcodeDTO;
import com.taxistore.pojo.Postcode;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;

public class PostcodeDao extends AbstractFormDao<Postcode>  {
    /** 對應的資料表名稱：car */
    private static final String TABLE_NAME = "postcode";
    /** 主鍵欄位名稱：carseq */
    private static final String KEY_NAME = "postcodeid";


    private static final String sql = """
              SELECT *
                FROM postcode p
            """;



    private static final String LikeCodeCondition=" WHERE p.postcodecode like CONCAT(?,'%') ORDER BY p.postcodeid";

    public PostcodeDao() {
        super(Postcode.class, TABLE_NAME, KEY_NAME);
    }

    public Result<List<PostcodeDTO>> getPostcodeByCodeLike(String CodePart) {
        String keyword =  CodePart + "%"; // 加上通配符
        return queryForList(PostcodeDTO.class, sql + LikeCodeCondition, CodePart);
    }
    /**
     * 測試 FeeDao 功能
     */
    public static void main(String[] args)
    {
        PostcodeDao dao = new PostcodeDao();
        Result<List<Postcode>> result0 =dao.getEntities();
        Result<List<PostcodeDTO>> result1 = dao.getPostcodeByCodeLike("1");


        Log.normal("123456");
    }
}

