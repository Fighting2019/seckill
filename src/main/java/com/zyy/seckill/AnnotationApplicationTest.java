package com.zyy.seckill;

import com.zyy.seckill.service.SeckillService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.zyy.seckill.service.*"})*/
public class AnnotationApplicationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AnnotationApplicationTest.class);
        SeckillService seckillService = annotationConfigApplicationContext.getBean(SeckillService.class);
        seckillService.testIncr();
    }
}
