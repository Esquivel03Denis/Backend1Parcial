package py.com.progweb.prueba.ejb;

import java.text.ParseException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.quartz.SchedulerException;

@Singleton
@LocalBean
@Startup
public class TareaProgramada{
    
    @EJB
    private ActualizaBolsas actualizaBolsas;

    @PostConstruct
    public void initSingleton() throws ParseException, SchedulerException {
        actualizaBolsas.initScheduler();
    }
}
