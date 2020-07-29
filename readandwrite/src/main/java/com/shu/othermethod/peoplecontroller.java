package com.shu.othermethod;

import com.shu.jpa.api.Student;
import com.shu.othermethod.po.Peopleread;
import com.shu.othermethod.po.Peoplewrite;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:   使用两个数据源来进行数据操作
 * @author: shurunlong
 * @create: 2020-07-28 17:50
 */
@Api(value = "people管理接口")
@RestController
@RequestMapping("/people/*")
public class peoplecontroller {

    @Autowired
    @Qualifier("myJdbcTemplatewrite")
    private JdbcTemplate jdbcTemplatewrite;

    @Autowired
    @Qualifier("myJdbcTemplateRead")
    private JdbcTemplate jdbcTemplateRead;


    @GetMapping ("findAll")
    public List<Peopleread> findAll() {

//        List<Peopleread> peoplereads = jdbcTemplateRead.queryForList("select * From people", Peopleread.class);
        List<Peopleread> peoplereads= jdbcTemplateRead.query("select * From people",new RowMapper<Peopleread>(){
            @Override
            public Peopleread mapRow(ResultSet arg0, int arg1) throws SQLException {
                Peopleread p = new Peopleread();
                p.setId(arg0.getInt("id"));
                p.setName(arg0.getString("name"));
                p.setCardNum(arg0.getString("cardNum"));
                return p;
            }
        });

        return peoplereads;
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Integer save(@RequestBody Peoplewrite people) throws Exception {
        String sql = "INSERT INTO people";
         sql = sql+" ( name , cardNum ) VALUES ( '"+people.getName()+"', '"+people.getCardNum()+"')";
//              execute : DDL   建表等
        //      update:   DML   增删改/
//         jdbcTemplatewrite.execute(sql);
        System.out.println(sql);
        int update = jdbcTemplatewrite.update(sql);


        return update;
    }
}
