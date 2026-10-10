package com.taxistore.service.impl;

import com.taxistore.dao.impl.PostcodeDao;
import com.taxistore.dto.PostcodeDTO;
import com.taxistore.pojo.Postcode;
import com.taxistore.pojo.Result;

import java.util.List;

public class PostcodeService {
    private final PostcodeDao dao = new PostcodeDao();

    public Result<List<PostcodeDTO>> getPostcodeByCodeLike(String codePart)
    {
        return dao.getPostcodeByCodeLike(codePart);
    }

    public Result<List<Postcode>>getPostcodes()
    {
        return dao.getEntities();
    }

}
