import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainMov {

	public static void main(String[] args) {
//		SessionFactory factory = new Configuration().addAnnotatedClass(Schedule.class).
//				addAnnotatedClass(Seance.class).configure().buildSessionFactory();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		session.getTransaction().begin();
		List<Seance> seances = new ArrayList<>();
		seances.add(new Seance("Indiana Johns",10));
		seances.add(new Seance("Indiana Johns2",1));
		seances.add(new Seance("Indiana Johns3",16));
		Schedule schedule = new Schedule(seances);
		session.persist(schedule);
		
		Seance seance = session.get(Seance.class, 1);
		seance.setSchedule(session.get(Schedule.class, 1));
		seance = session.get(Seance.class, 2);
		seance.setSchedule(session.get(Schedule.class, 1));
		seance = session.get(Seance.class, 3);
		seance.setSchedule(session.get(Schedule.class, 1));
		
		System.out.println(session.createQuery("from Schedule").list());
		
		session.getTransaction().commit();
		session.close();
		factory.close();
	}

}
