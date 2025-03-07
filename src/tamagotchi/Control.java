package tamagotchi;

public interface Control {
    void toFeed(Food food);

    void toClean();

    void toPlay(Play play);

    void toSleep();

    void toHeal();

    interface Food {

        Kind kind();

        Value value();

        enum Kind {
            SNACK, MEAL
        }

        record Value(int heals, int hanger, int weight, int happiness) {

        }
    }

    interface Play {
        record Empty() implements Play {

        }
    }
}
