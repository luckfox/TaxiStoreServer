package com.taxistore.dao;

import com.taxistore.pojo.Situation;

public interface SituationDao
{
    public Boolean update(Situation s);
    public Boolean add(Situation s);
}
