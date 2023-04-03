package py.com.progweb.prueba.ejb;

import java.text.ParseException;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@Singleton
@LocalBean
public class ActualizaBolsas {

    @Asynchronous
    public void initScheduler() throws ParseException, SchedulerException{

        try{

            // start the Scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            // create the Job
            JobDetail job = JobBuilder.newJob(ActualizaBolsasDao.class).
                withIdentity("ActualizaBolsasDao").build();

            // create the Schedule, run every 5 seconds
            ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.
                simpleSchedule().
                withIntervalInMinutes(1).
                repeatForever();

            // create the Trigger
            Trigger trigger = TriggerBuilder.newTrigger().
            withIdentity("QuickQuartzTrigger").
            withSchedule(scheduleBuilder).
            startNow().build();

            // schedule the Job
            scheduler.scheduleJob(job, trigger);

            // run for 5 minutes and exit
            Thread.sleep(1000 * 300);
            scheduler.shutdown();
        } catch (InterruptedException ex) {
            System.out.println("Error al realizar tarea programada "+ex);   
        }
    }
}
