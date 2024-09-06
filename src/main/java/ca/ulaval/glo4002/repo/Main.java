package ca.ulaval.glo4002.repo;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;

public class Main {

    public static final int CAR_ID = 237;

    // Version manuelle, en exemple. Vous pouvez décommenter celle-ci et commenter la suivante pour tester.
//    public static void main(String[] args) {
//        CarRepository repo = switch (System.getProperty("persistence", "json")) {
//            case "json" -> new CarJsonRepository();
//            case "hibernate" -> new CarHibernateRepository();
//            case "jdbc" -> new CarJdbcRepository();
//            default -> throw new IllegalArgumentException("Unknown persistence type");
//        };
//
//        CarService service = new CarService(repo);
//        String carDescription = service.getCarDescription(CAR_ID);
//        System.out.println(carDescription);
//    }

    // Version avec un IoC
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        CarService service = injector.getInstance(CarService.class);
        String carDescription = service.getCarDescription(CAR_ID);
        System.out.println(carDescription);
    }

    public static class AppModule extends AbstractModule {
        @Override
        protected void configure() {
            switch (System.getProperty("persistence", "json")) {
                case "json" -> bind(CarRepository.class).to(CarJsonRepository.class);
                case "hibernate" -> bind(CarRepository.class).to(CarHibernateRepository.class);
                case "jdbc" -> bind(CarRepository.class).to(CarJdbcRepository.class);
                default -> throw new IllegalArgumentException("Unknown persistence type");
            }

            bind(CarService.class);
        }
    }
}
