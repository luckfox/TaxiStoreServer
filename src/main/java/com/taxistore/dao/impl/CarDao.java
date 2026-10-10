
package com.taxistore.dao.impl;

import com.taxistore.dao.ICarDao;
import com.taxistore.pojo.Car;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/*
 * CarDao 是資料存取層（DAO）的實作，用來操作 Car 實體對應的資料表。
 * 繼承自 AbstractFormDao，使用泛型 T = Car，並實作 ICarDao 介面。
 *
 * <p>本類別負責將 Car 實體插入至資料庫中的 car 資料表。
 * 實作 insertEntity 方法時，透過父類別的通用邏輯處理欄位組合與資料插入，
 * 並提供 getExtraFieldValue 方法處理額外欄位的值，例如 createdate。</p>
 *
 * @author
 */
public class CarDao extends AbstractFormDao<Car> implements ICarDao {
    /** 對應的資料表名稱：car */
    private static final String TABLE_NAME = "car";
    /** 主鍵欄位名稱：carseq */
    private static final String KEY_NAME = "carseq";


    //private static final List<String> extraFields = List.of("createdate");
    // 使用 Map 儲存 extra 欄位與其賦值邏輯的對應關係
    private static final Map<String, Supplier<Object>> extraFields = Map.of(
            "createdate", () -> new Timestamp(System.currentTimeMillis())
    );

    public CarDao() {
        super(Car.class, TABLE_NAME,KEY_NAME,extraFields);
    }

    /*
     * 回傳對應欄位的額外欄位值（如 createdate）。
     * 此方法會在父類別的 insertEntity 呼叫時用來組合 SQL 的參數。
     *
     * @param fieldName 欲設定的欄位名稱
     * @return 欄位值，例如 Timestamp.now()；若欄位未定義，則傳回 null。
     */
    @Override
    protected Object getExtraFieldValue(String fieldName)
    {
        // 檢查是否有對應的額外欄位，如果有則返回其值
        Supplier<Object> valueSupplier = extraFields.get(fieldName);
        return valueSupplier != null ? valueSupplier.get() : null;
    }

    public Result<List<Car>> getCarsByCarnoLike(String carnoPart)
    {
        return getEntitiesByFieldLike("carno",carnoPart);
    }
    //=========================================================================================================
    public static void main(String[] args)
    {
         Car car=new Car();
        car.setCreatedate(new Timestamp(System.currentTimeMillis()));
        car.setSerialno("2345");
        car.setOrlicense("TDX-1234");
        car.setLicensestatus("1");
        car.setCarno("TDX-2416");
        car.setCarBrand(19L);
        car.setCaryear(new Timestamp(System.currentTimeMillis()));
        car.setCarcolor("黃");
        car.setPipeamount(1798);
        car.setMechanicss("1ZZA061183");
        car.setShingledat(new Timestamp(System.currentTimeMillis()));
        car.setCanceldate(new Timestamp(System.currentTimeMillis()));
        car.setCarlineno("ZE1~5085993 ");
        car.setCpdate(new Timestamp(System.currentTimeMillis()));
        car.setCpstatus("N");
        car.setPaid_security_deposit(0);
        car.setPre_security_deposit(0);
        car.setCheck_date(new Timestamp(System.currentTimeMillis()));
        car.setOkcar(new Timestamp(System.currentTimeMillis()));
        car.setRemark("");
        car.setCompanyseq(1234L);
        car.setAcco(62L);
        car.setAccdate(new Timestamp(System.currentTimeMillis()));
        car.setAcc_amount(10000);
        car.setAcccount(20000);
        car.setPayamount(20000);
        car.setTotalamount(20000);
        car.setEnddate(new Timestamp(System.currentTimeMillis()));
        car.setStopamount(20000);
        car.setServ(10L);
        car.setServicedate(new Timestamp(System.currentTimeMillis()));
        car.setStopservice(new Timestamp(System.currentTimeMillis()));
        car.setMonthamount(10);

        car.setAny_serv(20L);
        car.setAny_serv_amount(10000);
        car.setAny_serv_no("312345");
        car.setAny_serv_end_date(new Timestamp(System.currentTimeMillis()));
        car.setAny_serv_start_date(new Timestamp(System.currentTimeMillis()));

        car.setForce_serv(20L);
        car.setForce_serv_amount(10000);
        car.setForce_serv_no("123456");
        car.setForce_serv_start_date(new Timestamp(System.currentTimeMillis()));
        car.setForce_serv_end_date(new Timestamp(System.currentTimeMillis()));

        car.setUnion_serv(20L);
        car.setUnion_serv_amount(10000);
        car.setUnion_serv_end_date(new Timestamp(System.currentTimeMillis()));
        car.setUnion_serv_start_date(new Timestamp(System.currentTimeMillis()));

        car.setBuy_amount(25000);
        car.setBuy_date(new Timestamp(System.currentTimeMillis()));
        car.setSell_amount(25000);
        car.setSell_date(new Timestamp(System.currentTimeMillis()));

        CarDao carDao=new CarDao();
        Result<Long> result=carDao.insertEntity(car);

        if(result.isSuccess()) {
            Log.normal(result.toString());
            car.setSell_amount(35000);
            Result<Integer> result1 = carDao.updateEntity(car);

            Result<Car> result2 = carDao.getEntity(result.getData());
            Result<Car> result4 = carDao.getPreviousEntity(result.getData());
            Result<Car> result3 = carDao.getNextEntity(result4.getData().getCarseq());
            Result<Integer> result5 = carDao.deleteEntity( result.getData());
        }
        
    }
}
