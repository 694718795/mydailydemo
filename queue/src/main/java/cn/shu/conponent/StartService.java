package cn.shu.conponent;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartService implements InitializingBean {

    @Autowired
    ThreadClass threadClass;

    @Override
    public void afterPropertiesSet() throws Exception {
        threadClass.startThread();
    }
}
