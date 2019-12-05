package ${groupId}.controller

import com.github.lyrric.lianjia.service.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource


@RestController
@RequestMapping(value = ["/"])
class TestController{

    /**
     * 周统计
     */
    @GetMapping(value = ["/test"])
    fun test():String{
        return "test"
    }




}