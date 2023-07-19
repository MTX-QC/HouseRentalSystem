package com.mtx.cotroller;


import com.mtx.model.entity.Topaid;
import com.mtx.service.PaidService;
import com.mtx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RequestMapping("/api/*")
@RestController
@CrossOrigin
public class PaidController {
    @Autowired
    private PaidService paidService;

    @RequestMapping(value = "topaidlistU", method = RequestMethod.GET)
    public Result topaidlistU(HttpServletRequest request) {
        String name = request.getParameter("name");
        Result result = paidService.findToPaidByU(name);

        return result;
    }

    @RequestMapping(value = "selectall", method = RequestMethod.GET)
    public HashMap<String, Object> selectall(HttpServletRequest request) {
        String date1 = request.getParameter("date1");
        String date = request.getParameter("date");
        String search = request.getParameter("search");
        Integer cur = Integer.parseInt(request.getParameter("cur"));
        Integer size = Integer.parseInt(request.getParameter("size"));
        String name = request.getParameter("name");
        HashMap<String, Object> map = paidService.selectall(search, date, date1, cur, size, name);

        return map;
    }

    @RequestMapping(value = "gotopay", method = RequestMethod.GET)
    public Result gotopay(HttpServletRequest request) {
        String id = request.getParameter("id");
        Result result = paidService.gotopay(id);
        return result;

    }

    @RequestMapping(value = "delpaid", method = RequestMethod.POST)
    public Result delpaid(@RequestBody Integer[] list) {

        Result result = paidService.delPaidById(list);

        return result;
    }
    /**
     * 以下为房东的租金信息
     *
     * @param request
     * @return
     */

/*    @RequestMapping(value = "zulist", method = RequestMethod.GET)
    public HashMap<String, Object> zulist(HttpServletRequest request) {
        String search = request.getParameter("search");
        Integer cur = Integer.parseInt(request.getParameter("cur"));
        Integer size = Integer.parseInt(request.getParameter("size"));
        HashMap<String, Object> map = paidService.findZulist(search, cur, size);
        return map;
    }*/

    //收租
    @RequestMapping(value = "addtopaid", method = RequestMethod.POST)
    public Result addtopaid(@RequestBody Topaid topaid) {

        Result result = paidService.addtopaid(topaid);
        return result;
    }

    @RequestMapping(value = "topaidlist", method = RequestMethod.GET)
    public Result topaidlist(HttpServletRequest request) {
        String search = request.getParameter("search");
        Result result = paidService.findAllTopaid(search);

        return result;
    }

}
