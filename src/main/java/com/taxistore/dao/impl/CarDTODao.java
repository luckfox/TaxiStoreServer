package com.taxistore.dao.impl;

import com.taxistore.dao.ICarDTODao;
import com.taxistore.pojo.Car;
import com.taxistore.dto.CarDTO;
import com.taxistore.pojo.Result;

import java.util.List;

/**
 * @author luckf
 */
public class CarDTODao extends AbstractFormDao<Car> implements ICarDTODao
{
    private static final String TABLE_NAME = "car";
    private static final String KEY_NAME = "carseq";

    private static final String sql= """
            SELECT 
            _c.*
            ,_s.driverseq as driverseq,_d.portable as driverPhone
            ,drivername as driverName,_d.specialcode as driverSC
            ,_d.birthday as driverBirthday,_d.id as driverID
            ,_brand.name as carBrandName,_brand.code as carBrandSC
            ,_owner.companyname as ownerName,_owner.specialcode as ownerSC
            ,co2.companyname as ownerCompanyName,co2.specialcode as ownerCompanySC
            ,_union.companyname as union_servName,_union.specialcode as union_servSC
            ,_force.companyname as force_servName,_force.specialcode as force_servSC
            ,_acco.companyname as accoName,_acco.specialcode as accoSC
            ,_serv.companyname as servName,_serv.specialcode as servSC
            ,_any.companyname as any_servName,_any.specialcode as any_servSC 
            FROM car as _c LEFT JOIN situation AS _s ON _c.carseq =_s.carseq 
            LEFT JOIN driver AS _d ON _s.driverseq= _d.driverseq 
            LEFT JOIN carbrand AS _brand ON _c.carBrand=_brand.CARBRANDID
            LEFT JOIN company AS _owner ON _c.companyseq=_owner.companyseq 
            LEFT JOIN company co2 ON _owner.owner = co2.companyseq
            LEFT JOIN company AS _acco ON _c.acco=_acco.companyseq 
            LEFT JOIN company AS _serv ON _c.serv=_serv.companyseq 
            LEFT JOIN company AS _union ON _c.union_serv=_union.companyseq 
            LEFT JOIN company AS _force ON _c.force_serv=_force.companyseq 
            LEFT JOIN company AS _any ON _c.any_serv=_any.companyseq 
            """;

    private static final String sql2="""
            SELECT 
            _c.*      
            ,_brand.name as carBrandName,_brand.code as carBrandSC
            ,_owner.companyname as ownerName,_owner.specialcode as ownerSC
            ,co2.companyname as ownerCompanyName,co2.specialcode as ownerCompanySC
            ,_union.companyname as union_servName,_union.specialcode as union_servSC
            ,_force.companyname as force_servName,_force.specialcode as force_servSC
            ,_acco.companyname as accoName,_acco.specialcode as accoSC
            ,_serv.companyname as servName,_serv.specialcode as servSC
            ,_any.companyname as any_servName,_any.specialcode as any_servSC 
            FROM car as _c 
            LEFT JOIN carbrand AS _brand ON _c.carBrand=_brand.CARBRANDID
            LEFT JOIN company AS _owner ON _c.companyseq=_owner.companyseq 
            LEFT JOIN company co2 ON _owner.owner = co2.companyseq
            LEFT JOIN company AS _acco ON _c.acco=_acco.companyseq 
            LEFT JOIN company AS _serv ON _c.serv=_serv.companyseq 
            LEFT JOIN company AS _union ON _c.union_serv=_union.companyseq 
            LEFT JOIN company AS _force ON _c.force_serv=_force.companyseq 
            LEFT JOIN company AS _any ON _c.any_serv=_any.companyseq 
            """;


    private static final String EquCondition=" WHERE _c.carseq= ? ;";
    private static final String DecCondition=" WHERE _c.carseq < ? ORDER BY _c.carseq DESC limit 1;";
    private static final String IncCondition=" WHERE _c.carseq > ? ORDER BY _c.carseq ASC limit 1;";
    private static final String FinalCondition="ORDER BY _c.carseq DESC LIMIT 0 , 1;";
    private static final String FirstCondition="ORDER BY _c.carseq LIMIT 1;";
    private static final String LikeCarNoCondition = " WHERE _c.carno LIKE ? ORDER BY _c.carseq DESC;";
    private static final String LikeMechanicssCondition = " WHERE _c.mechanicss LIKE ? ORDER BY _c.mechanicss DESC;";
    private static final String LikeCarLineNoCondition = " WHERE _c.carlineno LIKE ? ORDER BY _c.carlineno DESC;";


    public CarDTODao() {
        super(Car.class, TABLE_NAME,KEY_NAME);
    }

    @Override
    public Result<CarDTO> getPreviousCarDTO(long serialNo)
    {
        return  queryForOne(CarDTO.class,sql+ DecCondition,serialNo);

    }
    @Override
    public Result<CarDTO> getNextCarDTO(long serialNo)
    {
        return  queryForOne(CarDTO.class,sql+ IncCondition,serialNo);

    }
    @Override
    public Result<CarDTO> getCarDTO(long serialNo)
    {
        return queryForOne(CarDTO.class,sql+EquCondition,serialNo);

    }
    @Override
    public Result<CarDTO> getLastCarDTO()
    {
        return queryForOne(CarDTO.class,sql+FinalCondition);

    }
    @Override
    public Result<CarDTO> getFirstCarDTO()
    {
        return queryForOne(CarDTO.class,sql+FirstCondition);

    }
    @Override
    public Result<List<CarDTO>> getCarDTOs()
    {
        return queryForList(CarDTO.class , sql+";");
    }

    @Override
    public Result<List<CarDTO>> getCarDTOsByCarNoLike(String carNoPart) {
        String keyword = "%" + carNoPart + "%"; // 加上通配符
        return queryForList(CarDTO.class, sql + LikeCarNoCondition, keyword);
    }
   // @Override
    public Result<List<CarDTO>> getCarDTOsByMechanicssPartLike(String MechanicssPart) {
        String keyword = "%" + MechanicssPart + "%"; // 加上通配符
        return queryForList(CarDTO.class, sql + LikeMechanicssCondition, keyword);
    }
    public Result<List<CarDTO>> getCarDTOsByCarlineNoPartLike(String CarlineNoPart) {
        String keyword = "%" + CarlineNoPart + "%"; // 加上通配符
        return queryForList(CarDTO.class, sql + LikeCarLineNoCondition, keyword);
    }

}
