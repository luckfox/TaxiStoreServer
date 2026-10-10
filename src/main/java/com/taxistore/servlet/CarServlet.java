package com.taxistore.servlet;

import com.google.gson.Gson;
import com.taxistore.dto.CarDTO;
import com.taxistore.pojo.*;
import com.taxistore.service.impl.CarBrandService;
import com.taxistore.service.impl.CarService;
import com.taxistore.service.impl.CarDTOService;
import com.utils.Log;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author luckf
 * @date 2022/10/25
 */
@WebServlet(name = "carServlet",urlPatterns = "/carServlet")
public class CarServlet extends BaseServlet
{
    final static CarDTOService carDTOService =new CarDTOService();
    final static CarService carService=new CarService();
    final static CarBrandService carBrand=new CarBrandService();


    /**
     * 接受來自於
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getFirstCarDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Log.debug("getFirstCarDTO@CarServlet");
        Result<CarDTO> result= carDTOService.getFirstCarDTO();
        request.setAttribute("CarDTO",result.getData());
        request.getRequestDispatcher("/pages/layout/form/createCarDB.jsp").forward(request,response);

    }
    /**
     * 接受來自於
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getLastCarDTO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        Log.debug("getLastCarDTO@CarServlet");
        Result<CarDTO> result= carDTOService.getLastCarDTO();
        request.setAttribute("CarDTO",result.getData());
        request.getRequestDispatcher("/pages/layout/form/createCarDB.jsp").forward(request,response);

    }
    protected  void ajaxGetCarDTO(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxGetCarDTO@CarServlet");
        long serialNo= WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<CarDTO> result=carDTOService.getCarDTO(serialNo);
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetFirstCarDTO(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxGetFirstCarDTO@CarServlet");

        Result<CarDTO> result= carDTOService.getFirstCarDTO();
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetLastCarDTO(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxGetLastCarDTO@CarServlet");

        Result<CarDTO> result= carDTOService.getLastCarDTO();
        response.getWriter().write(result.toJSON());
    }
    protected void ajaxGetNextCarDTO(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxGetNextCarDTO@CarServlet");
        long serialNo= WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<CarDTO> result= carDTOService.getNextCarDTO(serialNo);
        response.getWriter().write(result.toJSON());
    }

    public void ajaxGetPreviousCarDTO(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxGetPreviousCarDTO@CarServlet");
        long serialNo= WebUtils.stringToInt(request.getParameter("serialNo"));
        Result<CarDTO> result= carDTOService.getPreviousCarDTO(serialNo);
        response.getWriter().write(result.toJSON());
    }

    public void ajaxGetCarDTOsByCarNoLike(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxGetCarDTOsByCarNoLike@CarServlet");
        String carNoPart=request.getParameter("carNoPart");
        Result<List<CarDTO>> result= carDTOService.getCarDTOsByCarNoLike(carNoPart);
        response.getWriter().write(result.toJSON());
    }
    public void ajaxGetCarsByCarnoLike(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxGetCarsByCarnoLike@CarServlet");
        String carNoPart=request.getParameter("carNoPart");
        Result<List<Car>> result= carService.getCarsByCarnoLike(carNoPart);
        response.getWriter().write(result.toJSON());
    }
    public void ajaxGetCarDTOsByMechanicssLike(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxGetCarDTOsByMechanicssLike@CarServlet");
        String MechanicssPart=request.getParameter("MechanicssPart");
        Result<List<CarDTO>> result= carDTOService.getCarDTOsByMechanicssPartLike(MechanicssPart);
        response.getWriter().write(result.toJSON());
    }
    public void ajaxGetCarDTOsByCarLineNoLike(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxGetCarDTOsByCarLineNoLike@CarServlet");
        String CarLineNoPart=request.getParameter("CarLineNoPart");
        Result<List<CarDTO>> result= carDTOService.getCarDTOsByCarLineNoPartLike(CarLineNoPart);
        response.getWriter().write(result.toJSON());
    }

    public void ajaxDeleteCarDTO(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.debug("ajaxDeleteCarDTO@CarServlet");
        Integer serialNo=WebUtils.stringToInt(request.getParameter("serialNo"));

        Result<CarDTO> result= carDTOService.deleteCar(serialNo);
        response.getWriter().write(result.toJSON());
    }
    public void ajaxUpdateItemCar(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Car c=new Car();
        System.out.println("ajaxUpdateItem2@CarServlet");
        WebUtils.copyParamToBean(request.getParameterMap(),c);
        System.out.println(c);
    }
    public void ajaxUpdateCar(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("ajaxUpdateItem@CarServlet");
        Map<String, Object> responseData = new HashMap<>();
        Car car=WebUtils.copyParamToBean(request.getParameterMap(),new Car());
        Log.debug(car.toString());

        responseData.put("success",false);
        if(car.getCarseq()==0)
        {
            responseData.put("mode", "append");
            Result<Long> result=carService.insertCar(car);
            if(result.isSuccess())
            {
                responseData.put("serialNo", String.valueOf(result.getData()));
                responseData.put("success", true);
            }
        }
        else
        {
            responseData.put("mode", "update");
            Result<Integer> result=carService.updateCar(car);
            if(result.isSuccess())
            {
                responseData.put("success",true);
            }
        }
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(responseData));

    }

    /**
     * 取得所有汽車廠牌的名單
     * @param request
     * @param response
     * @throws IOException
     */
    public void getCarBrand(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Log.normal("getCarBrand");
        Result<List<CarBrand>> result =carBrand.getAllBrands();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result.toJSON());

    }
    public void getCarBrandBySpecialCode(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        String specCode=request.getParameter("specCode");
        Result<List<CarBrand>> result=carBrand.getCarBrandBySpecialCode(specCode);
        response.getWriter().write(result.toJSON());
    }
    public void IsCardIdExist(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String carId=request.getParameter("carId");
        //carService.

    }

}
