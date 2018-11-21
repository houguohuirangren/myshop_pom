package com.qf.myshop.myshop_back;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FdfsClientConfig.class)
public class MyshopBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyshopBackApplication.class, args);
    }
}
